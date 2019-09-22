package com.shagalalab.qarejet.ui.splash

import com.shagalalab.qarejet.core.utils.Category
import com.shagalalab.qarejet.domain.interactor.account.AddAccountsUseCase
import com.shagalalab.qarejet.domain.interactor.category.AddCategoriesUseCase
import com.shagalalab.qarejet.domain.interactor.config.InitialDataUseCase
import com.shagalalab.qarejet.domain.model.Account
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

    fun checkDataPopulated(accounts: List<Account>, categories: List<Category>) {
        val isPopulated = initialDataUseCase.isDataPopulated()

        if (isPopulated) {
            view.goToNextScreen()
        } else {
            addAccountsUseCase.execute(accounts)
                    .mergeWith(addCategoriesUseCase.execute(categories))
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