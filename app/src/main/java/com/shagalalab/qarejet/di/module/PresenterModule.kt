package com.shagalalab.qarejet.di.module

import com.shagalalab.qarejet.di.AppSchedulers
import com.shagalalab.qarejet.domain.interactor.account.AddAccountsUseCase
import com.shagalalab.qarejet.domain.interactor.account.GetAllAccountsUseCase
import com.shagalalab.qarejet.domain.interactor.category.AddCategoriesUseCase
import com.shagalalab.qarejet.domain.interactor.category.GetAllCategoriesUseCase
import com.shagalalab.qarejet.domain.interactor.config.InitialDataUseCase
import com.shagalalab.qarejet.domain.interactor.transaction.AddTransactionUseCase
import com.shagalalab.qarejet.domain.interactor.transaction.GetAllTransactionsUseCase
import com.shagalalab.qarejet.ui.splash.SplashPresenter
import com.shagalalab.qarejet.ui.transaction.create.NewTransactionPresenter
import com.shagalalab.qarejet.ui.transaction.list.TransactionListPresenter
import com.shagalalab.qarejet.util.SchedulersProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {

    @Provides
    @Singleton
    fun providesSchedulers(): SchedulersProvider = AppSchedulers()

    @Provides
    @Singleton
    fun providesNewTransactionPresenter(
        addTransactionUseCase: AddTransactionUseCase,
        getAllAccountsUseCase: GetAllAccountsUseCase,
        getAllCategoriesUseCase: GetAllCategoriesUseCase,
        schedulersProvider: SchedulersProvider
    ) = NewTransactionPresenter(addTransactionUseCase, getAllAccountsUseCase, getAllCategoriesUseCase, schedulersProvider)

    @Provides
    @Singleton
    fun providesSplashPresenter(initialDataCase: InitialDataUseCase,
        addAccountsUseCase: AddAccountsUseCase,
        addCategoriesUseCase: AddCategoriesUseCase,
        schedulersProvider: SchedulersProvider) =
        SplashPresenter(initialDataCase, addAccountsUseCase, addCategoriesUseCase, schedulersProvider)

    @Provides
    @Singleton
    fun providesTransactionListPresenter(getAllTransactionsUseCase: GetAllTransactionsUseCase,
        schedulersProvider: SchedulersProvider) = TransactionListPresenter(getAllTransactionsUseCase, schedulersProvider)
}