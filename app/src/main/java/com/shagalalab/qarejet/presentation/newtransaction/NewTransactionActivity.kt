package com.shagalalab.qarejet.presentation.newtransaction

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.shagalalab.qarejet.QarejetApp
import com.shagalalab.qarejet.R
import javax.inject.Inject

/**
 * Created by atabek on 12/11/2017.
 */

class NewTransactionActivity : AppCompatActivity() {
    @Inject lateinit var presenter: NewTransactionPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_transaction)
        (application as QarejetApp).component.inject(this)

        presenter.fetchNewTransactions()
    }
}