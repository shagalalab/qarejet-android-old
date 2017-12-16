package com.shagalalab.qarejet.presentation.newtransaction

import android.util.Log
import com.shagalalab.qarejet.domain.interactor.transaction.NewTransactionUseCase
import com.shagalalab.qarejet.domain.model.Transaction
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by atabek on 12/16/2017.
 */

class NewTransactionPresenter constructor(
        var newTransactionUseCase : NewTransactionUseCase
) {
    fun fetchNewTransactions() {
        newTransactionUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onFailure)
    }

    fun onSuccess(transactions: List<Transaction>) {
        Log.d("mytest", "size: " + transactions.size)
    }

    fun onFailure(throwable: Throwable) {
        //show error message
        Log.e("mytest", "Error happened", throwable)
    }
}