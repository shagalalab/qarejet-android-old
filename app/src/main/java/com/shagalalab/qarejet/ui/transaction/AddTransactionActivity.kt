package com.shagalalab.qarejet.ui.transaction

import android.animation.ValueAnimator
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.TextViewCompat
import android.support.v4.widget.TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.shagalalab.qarejet.QarejetApp
import com.shagalalab.qarejet.R
import com.shagalalab.qarejet.domain.model.Account
import com.shagalalab.qarejet.domain.model.Category
import com.shagalalab.qarejet.ui.widget.CategoryAdapter
import com.shagalalab.qarejet.ui.widget.picker.DatePickerFragment
import com.shagalalab.qarejet.ui.widget.numberkeyboard.NumberKeyboardView
import com.shagalalab.qarejet.ui.widget.picker.TimePickerFragment
import com.shagalalab.qarejet.util.Constants.TRANSACTION_TYPE_EXPENSE
import com.shagalalab.qarejet.util.getCurrencySign
import com.shagalalab.qarejet.util.isToday
import com.shagalalab.qarejet.util.toShortDate
import com.shagalalab.qarejet.util.toShortTime
import kotlinx.android.synthetic.main.activity_new_transaction.*
import java.util.Calendar
import javax.inject.Inject

class AddTransactionActivity : AppCompatActivity(), AddTransactionView, TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener {
    @Inject lateinit var presenter: AddTransactionPresenter

    private lateinit var accountsAdapter: ArrayAdapter<Account>
    private lateinit var categoryAdapter: CategoryAdapter

    private var selectedDate = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_transaction)
        (application as QarejetApp).component.inject(this)

        setSupportActionBar(transactionToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        presenter.init(this)
        presenter.requestAccountData()
        presenter.requestCategoryData()

        setDateText()
        setTimeText()
        setClickListeners()

        TextViewCompat.setAutoSizeTextTypeWithDefaults(transactionAmount, AUTO_SIZE_TEXT_TYPE_UNIFORM)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun setClickListeners() {
        transactionTypeIncome.setOnClickListener { presenter.setTransactionTypeToIncome() }
        transactionTypeExpense.setOnClickListener { presenter.setTransactionTypeToExpense() }
        transactionCardDateText.setOnClickListener { presenter.chooseDate() }
        transactionCardTimeText.setOnClickListener { presenter.chooseTime() }
        transactionKeyboard.setNumberListener(object : NumberKeyboardView.NumberListener {
            override fun onShowMessage(message: String) {
                Toast.makeText(this@AddTransactionActivity, message, Toast.LENGTH_SHORT).show()
            }

            override fun onNumberTextChanged(changedText: String) {
                transactionAmount.text = changedText
            }

            override fun onNumberKeyboardChanged(isShowing: Boolean, height: Float, animationDuration: Long) {
                val h = if (isShowing) -height else height

                ValueAnimator.ofFloat(0f, h).apply {
                    duration = animationDuration
                    val y = transactionBelowKeyboard.y
                    addUpdateListener {
                        transactionBelowKeyboard.y = y + (it.animatedValue as Float)
                    }
                    start()
                }
            }
        })
        addTransaction.setOnClickListener({
            presenter.addNewTransaction(
                    transactionAmount.text.toString().toDouble(),
                    transactionCardAccountSpinner.selectedItem as Account,
                    transactionCardCategoryList.selectedItem as Category,
                    transactionCardNoteText.text.toString(),
                    selectedDate.time
            )
        })
    }

    private fun setDateText() {
        if (selectedDate.isToday()) {
            transactionCardDateText.setText(R.string.today)
        } else {
            transactionCardDateText.setText(selectedDate.toShortDate())
        }
    }

    private fun setTimeText() {
        transactionCardTimeText.setText(selectedDate.toShortTime())
    }

    override fun updateAccounts(accounts: List<Account>) {
        accountsAdapter = ArrayAdapter(this, R.layout.item_spinner, accounts)
        accountsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        transactionCardAccountSpinner.adapter = accountsAdapter

        transactionCurrencySign.text = accounts[0].getCurrencySign()
    }

    override fun updateCategories(categories: List<Category>) {
        categoryAdapter = CategoryAdapter(this, categories)
        categoryAdapter.filterByType(TRANSACTION_TYPE_EXPENSE)
        transactionCardCategoryList.adapter = categoryAdapter
    }

    override fun setSignToPlus() {
        transactionAmountSign.setImageResource(R.drawable.ic_add_white_24dp)
    }

    override fun setSignToMinus() {
        transactionAmountSign.setImageResource(R.drawable.ic_remove_white_24dp)
    }

    override fun filterCategories(type: Int) {
        transactionCardCategoryList.setSelection(0)
        categoryAdapter.filterByType(type)
    }

    override fun finishActivity() {
        finish()
    }

    override fun showDateChooser() {
        val datePicker = DatePickerFragment()
        datePicker.init(selectedDate, this)
        datePicker.show(supportFragmentManager, "datePicker")
    }

    override fun showTimeChooser() {
        val timePicker = TimePickerFragment()
        timePicker.init(selectedDate, this)
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

    override fun showError(message: String) {
        Snackbar.make(transactionAmount, message, Snackbar.LENGTH_LONG).show()
    }

    override fun showError(message: Int) {
        Snackbar.make(transactionAmount, message, Snackbar.LENGTH_LONG).show()
    }
}