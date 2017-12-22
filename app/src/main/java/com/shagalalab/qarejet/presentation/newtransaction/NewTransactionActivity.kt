package com.shagalalab.qarejet.presentation.newtransaction

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ArrayAdapter
import com.shagalalab.qarejet.QarejetApp
import com.shagalalab.qarejet.R
import com.shagalalab.qarejet.domain.model.Account
import com.shagalalab.qarejet.domain.model.Category
import com.shagalalab.qarejet.domain.model.Transaction
import kotlinx.android.synthetic.main.activity_new_transaction.*
import java.util.*
import javax.inject.Inject

/**
 * Created by atabek on 12/11/2017.
 */

class NewTransactionActivity : AppCompatActivity() {
    @Inject lateinit var presenter: NewTransactionPresenter
    @Inject lateinit var accounts: Array<Account>
    @Inject lateinit var categories: Array<Category>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_transaction)
        (application as QarejetApp).component.inject(this)

        val accountsAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, accounts)
        accountsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        transactionCardAccountSpinner.adapter = accountsAdapter

        val categoryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        transactionCardCategoryList.adapter = categoryAdapter

        addTransaction.setOnClickListener({
            Log.d("mytest", "account: " + transactionCardAccountSpinner.selectedItem)
            Log.d("mytest", "category: " + transactionCardCategoryList.selectedItem)
            Log.d("mytest", "note: " + transactionCardNoteText.text)

            presenter.addNewTransaction(Transaction(0, "type", Date(),
                    1, 1, 242.33, transactionCardNoteText.text.toString()))
        })
    }
}