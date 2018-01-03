package com.shagalalab.qarejet.ui.splash

import com.shagalalab.qarejet.domain.interactor.account.AddAccountsUseCase
import com.shagalalab.qarejet.domain.interactor.category.AddCategoriesUseCase
import com.shagalalab.qarejet.domain.interactor.config.InitialDataUseCase
import com.shagalalab.qarejet.domain.model.Account
import com.shagalalab.qarejet.domain.model.Category
import com.shagalalab.qarejet.util.Constants.TRANSACTION_TYPE_EXPENSE
import com.shagalalab.qarejet.util.Constants.TRANSACTION_TYPE_INCOME
import com.shagalalab.qarejet.util.SchedulersProvider

class SplashPresenter constructor(
        private val initialDataUseCase: InitialDataUseCase,
        private val addAccountsUseCase: AddAccountsUseCase,
        private val addCategoriesUseCase: AddCategoriesUseCase,
        private val schedulersProvider: SchedulersProvider
) {
    lateinit var view: SplashView

    fun init(view: SplashView) {
        this.view = view
    }

    fun checkDataPopulated(accounts: Array<String>, categoriesExpense: Array<String>, categoriesIncome: Array<String>) {
        val isPopulated = initialDataUseCase.isDataPopulated()

        if (isPopulated) {
            view.goToNextScreen()
        } else {
            addAccountsUseCase.execute(accounts.map { Account(0, it) })
                    .mergeWith(addCategoriesUseCase.execute(
                            categoriesExpense.map { Category(0, it, TRANSACTION_TYPE_EXPENSE) }.plus(
                                    categoriesIncome.map { Category(0, it, TRANSACTION_TYPE_INCOME) }
                            ))
                    )
                    .subscribeOn(schedulersProvider.io())
                    .observeOn(schedulersProvider.ui())
                    .subscribe(this::allDataSaved, this::failureToSaveData)
        }
    }

    private fun allDataSaved() {
        initialDataUseCase.setInitialDataPopulated()
        view.goToNextScreen()
    }

    private fun failureToSaveData(throwable: Throwable) {
        view.showError("Error saving data: " + throwable.localizedMessage)
    }

}