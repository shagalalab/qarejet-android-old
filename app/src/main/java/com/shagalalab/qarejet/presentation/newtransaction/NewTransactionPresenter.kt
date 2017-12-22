package com.shagalalab.qarejet.presentation.newtransaction

import android.util.Log
import com.shagalalab.qarejet.domain.interactor.transaction.AddNewTransactionUseCase
import com.shagalalab.qarejet.domain.model.Transaction
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by atabek on 12/16/2017.
 */

class NewTransactionPresenter constructor(
        private var addNewTransactionsUseCase: AddNewTransactionUseCase
) {

    fun addNewTransaction(transaction: Transaction) {
        addNewTransactionsUseCase.execute(transaction)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onFailure)
    }

    private fun onSuccess() {
        Log.d("mytest", "Transaction added")
    }

    private fun onFailure(throwable: Throwable) {
        //show error message
        Log.e("mytest", "Error happened", throwable)
    }
}