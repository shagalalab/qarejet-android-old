package com.shagalalab.qarejet.ui.transaction

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.shagalalab.qarejet.QarejetApp
import com.shagalalab.qarejet.R
import com.shagalalab.qarejet.domain.model.Account
import com.shagalalab.qarejet.domain.model.Category
import com.shagalalab.qarejet.ui.widget.DatePickerFragment
import com.shagalalab.qarejet.ui.widget.TimePickerFragment
import kotlinx.android.synthetic.main.activity_new_transaction.*
import java.text.DateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by atabek on 12/11/2017.
 */

class NewTransactionActivity : AppCompatActivity(), NewTransactionView, TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener {
    @Inject lateinit var presenter: NewTransactionPresenter

    private lateinit var accountsAdapter: ArrayAdapter<Account>
    private lateinit var categoryAdapter: ArrayAdapter<Category>

    private var selectedDate = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_transaction)
        (application as QarejetApp).component.inject(this)

        presenter.init(this)
        presenter.requestAccountData()
        presenter.requestCategoryData()

        setDateText()
        setTimeText()
        setClickListeners()
    }

    private fun setClickListeners() {
        transactionTypeIncome.setOnClickListener { presenter.setTransactionTypeToIncome() }
        transactionTypeExpense.setOnClickListener { presenter.setTransactionTypeToExpense() }
        transactionCardDateText.setOnClickListener { presenter.chooseDate() }
        transactionCardTimeText.setOnClickListener { presenter.chooseTime() }
        addTransaction.setOnClickListener({
            presenter.addNewTransaction(
                    transactionAmount.text.toString().toDouble(),
                    (transactionCardAccountSpinner.selectedItem as Account).id,
                    (transactionCardCategoryList.selectedItem as Category).id,
                    transactionCardNoteText.text.toString(),
                    selectedDate.time
            )
        })
    }

    private fun setDateText() {
        transactionCardDateText.setText(DateFormat.getDateInstance(DateFormat.SHORT).format(selectedDate.time))
    }

    private fun setTimeText() {
        transactionCardTimeText.setText(DateFormat.getTimeInstance(DateFormat.SHORT).format(selectedDate.time))
    }

    override fun updateAccounts(accounts: List<Account>) {
        accountsAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, accounts)
        accountsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        transactionCardAccountSpinner.adapter = accountsAdapter
    }

    override fun updateCategories(categories: List<Category>) {
        categoryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        transactionCardCategoryList.adapter = categoryAdapter
    }

    override fun finishActivity() {
        finish()
    }

    override fun showDateChooser() {
        val datePicker = DatePickerFragment()
        datePicker.setDateListener(this)
        datePicker.show(supportFragmentManager, "datePicker")
    }

    override fun showTimeChooser() {
        val timePicker = TimePickerFragment()
        timePicker.setTimeListener(this)
        timePicker.show(supportFragmentManager, "timePicker")
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        selectedDate.set(Calendar.HOUR_OF_DAY, hourOfDay)
        selectedDate.set(Calendar.MINUTE, minute)
        setTimeText()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        selectedDate.set(Calendar.YEAR, year)
        selectedDate.set(Calendar.MONTH, month)
        selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        setDateText()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}