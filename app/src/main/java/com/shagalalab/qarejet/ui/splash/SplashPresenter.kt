package com.shagalalab.qarejet.ui.splash

import android.util.Log
import com.shagalalab.qarejet.domain.interactor.account.AddAccountsUseCase
import com.shagalalab.qarejet.domain.interactor.category.AddCategoriesUseCase
import com.shagalalab.qarejet.domain.interactor.config.InitialDataUseCase
import com.shagalalab.qarejet.domain.model.Account
import com.shagalalab.qarejet.domain.model.Category
import com.shagalalab.qarejet.util.Constants.TRANSACTION_TYPE_EXPENSE
import com.shagalalab.qarejet.util.Constants.TRANSACTION_TYPE_INCOME
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SplashPresenter constructor(
        private val initialDataUseCase: InitialDataUseCase,
        private val addAccountsUseCase: AddAccountsUseCase,
        private val addCategoriesUseCase: AddCategoriesUseCase
) {
    lateinit var view: SplashView

    fun init(view: SplashView) {
        this.view = view
    }

    fun checkDataPopulated(accounts: Array<String>, categoriesExpense: Array<String>, categoriesIncome: Array<String>) {
        val isPopulated = initialDataUseCase.isDataPopulated()

        if (isPopulated) {
            initialDataUseCase.setInitialDataPopulated()
            view.goToNextScreen()
        } else {
            addAccountsUseCase.execute(accounts.map { Account(0, it) })
                    .mergeWith(addCategoriesUseCase.execute(
                            categoriesExpense.map { Category(0, it, TRANSACTION_TYPE_EXPENSE) }.plus(
                                    categoriesIncome.map { Category(0, it, TRANSACTION_TYPE_INCOME) }
                            ))
                    )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::allDataSaved, this::failureToSaveData)
        }
    }

    private fun allDataSaved() {
        Log.d("mytest", "allDataSaved")
        initialDataUseCase.setInitialDataPopulated()
        view.goToNextScreen()
    }

    private fun failureToSaveData(throwable: Throwable) {
        view.showError("Error saving data")
        Log.e("mytest", "Error saving data", throwable)
    }

}