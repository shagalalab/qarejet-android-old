package com.shagalalab.qarejet.ui.transaction

import com.shagalalab.qarejet.R
import com.shagalalab.qarejet.domain.interactor.account.GetAllAccountsUseCase
import com.shagalalab.qarejet.domain.interactor.category.GetAllCategoriesUseCase
import com.shagalalab.qarejet.domain.interactor.transaction.AddTransactionUseCase
import com.shagalalab.qarejet.domain.model.Account
import com.shagalalab.qarejet.domain.model.Category
import com.shagalalab.qarejet.domain.model.Transaction
import com.shagalalab.qarejet.util.Constants.TRANSACTION_TYPE_EXPENSE
import com.shagalalab.qarejet.util.Constants.TRANSACTION_TYPE_INCOME
import com.shagalalab.qarejet.util.SchedulersProvider
import java.util.Date

class AddTransactionPresenter constructor(
        private val addTransactionsUseCase: AddTransactionUseCase,
        private val getAllAccountsUseCase: GetAllAccountsUseCase,
        private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
        private val schedulersProvider: SchedulersProvider
) {
    private lateinit var view: AddTransactionView
    private var transactionType = TRANSACTION_TYPE_EXPENSE

    fun init(view: AddTransactionView) {
        this.view = view
    }

    fun requestAccountData() {
        getAllAccountsUseCase.execute()
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
                .subscribe(view::updateAccounts)
    }

    fun requestCategoryData() {
        getAllCategoriesUseCase.execute()
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
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

    fun addNewTransaction(amount: Double, account: Account, category: Category, note: String, date: Date) {
        if (amount == 0.0) {
            view.showError(R.string.enter_amount)
            return
        }
        addTransactionsUseCase.execute(Transaction(0, transactionType, date, account, category, amount, note))
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
                .subscribe(this::onSuccess, this::onFailure)
    }

    private fun onSuccess() {
        view.finishActivity()
    }

    private fun onFailure(throwable: Throwable) {
        view.showError("Error happened: " + throwable.localizedMessage)
    }
}