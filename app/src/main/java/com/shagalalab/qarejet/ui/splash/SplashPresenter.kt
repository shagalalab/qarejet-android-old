package com.shagalalab.qarejet.ui.splash

import android.util.Log
import com.shagalalab.qarejet.domain.interactor.account.AddAccountsUseCase
import com.shagalalab.qarejet.domain.interactor.category.AddCategoriesUseCase
import com.shagalalab.qarejet.domain.interactor.config.InitialDataUseCase
import com.shagalalab.qarejet.domain.model.Account
import com.shagalalab.qarejet.domain.model.Category
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

    fun checkDataPopulated(accounts: Array<String>, categories: Array<String>) {
        val isPopulated = initialDataUseCase.isDataPopulated()

        if (isPopulated) {
            initialDataUseCase.setInitialDataPopulated()
            view.goToNextScreen()
        } else {
            addAccountsUseCase.execute(accounts.map { Account(0, it) })
                    .mergeWith(addCategoriesUseCase.execute(categories.map { Category(0, it) }))
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