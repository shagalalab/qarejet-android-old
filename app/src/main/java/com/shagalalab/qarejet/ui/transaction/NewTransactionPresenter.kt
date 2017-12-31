package com.shagalalab.qarejet.ui.transaction

import com.shagalalab.qarejet.R
import com.shagalalab.qarejet.domain.interactor.account.GetAllAccountsUseCase
import com.shagalalab.qarejet.domain.interactor.category.GetAllCategoriesUseCase
import com.shagalalab.qarejet.domain.interactor.transaction.AddNewTransactionUseCase
import com.shagalalab.qarejet.domain.model.Transaction
import com.shagalalab.qarejet.util.Constants.TRANSACTION_TYPE_EXPENSE
import com.shagalalab.qarejet.util.Constants.TRANSACTION_TYPE_INCOME
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * Created by atabek on 12/16/2017.
 */

class NewTransactionPresenter constructor(
        private val addNewTransactionsUseCase: AddNewTransactionUseCase,
        private val getAllAccountsUseCase: GetAllAccountsUseCase,
        private val getAllCategoriesUseCase: GetAllCategoriesUseCase
) {
    private lateinit var view: NewTransactionView
    private var transactionType = TRANSACTION_TYPE_EXPENSE

    fun init(view: NewTransactionView) {
        this.view = view
    }

    fun requestAccountData() {
        getAllAccountsUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::updateAccounts)
    }

    fun requestCategoryData() {
        getAllCategoriesUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::updateCategories)
    }

    fun setTransactionTypeToIncome() {
        transactionType = TRANSACTION_TYPE_INCOME
        view.setSignToPlus()
        view.filterCategories(transactionType)
    }

    fun setTransactionTypeToExpense() {
        transactionType = TRANSACTION_TYPE_EXPENSE
        view.setSignToMinus()
        view.filterCategories(transactionType)
    }

    fun chooseDate() {
        view.showDateChooser()
    }

    fun chooseTime() {
        view.showTimeChooser()
    }

    fun addNewTransaction(amount: Double, accountId: Long, categoryId: Long, note: String, date: Date) {
        if (amount == 0.0) {
            view.showError(R.string.enter_amount)
            return
        }
        addNewTransactionsUseCase.execute(Transaction(0, transactionType, date, accountId, categoryId, amount, note))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onFailure)
    }

    private fun onSuccess() {
        view.finishActivity()
    }

    private fun onFailure(throwable: Throwable) {
        view.showError("Error happened: " + throwable.localizedMessage)
    }
}