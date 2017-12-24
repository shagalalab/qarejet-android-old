package com.shagalalab.qarejet.ui.transaction

import android.util.Log
import com.shagalalab.qarejet.domain.interactor.account.GetAllAccountsUseCase
import com.shagalalab.qarejet.domain.interactor.category.GetAllCategoriesUseCase
import com.shagalalab.qarejet.domain.interactor.transaction.AddNewTransactionUseCase
import com.shagalalab.qarejet.domain.model.Transaction
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * Created by atabek on 12/16/2017.
 */

class NewTransactionPresenter constructor(
        private val addNewTransactionsUseCase: AddNewTransactionUseCase,
        private val getAllAccountsUseCase: GetAllAccountsUseCase,
        private val getAllCategoriesUseCase: GetAllCategoriesUseCase
) {
    fun checkDataExists() {
        getAllAccountsUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer { Log.d("mytest", "accounts: " + it.size) })

        getAllCategoriesUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer { Log.d("mytest", "categories: " + it.size) })
    }


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