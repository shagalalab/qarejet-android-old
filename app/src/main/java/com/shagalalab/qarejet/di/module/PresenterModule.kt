package com.shagalalab.qarejet.di.module

import com.shagalalab.qarejet.domain.interactor.transaction.NewTransactionUseCase
import com.shagalalab.qarejet.presentation.newtransaction.NewTransactionPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by atabek on 12/16/2017.
 */

@Module
class PresenterModule {

    @Provides
    @Singleton
    fun provideNewTransactionPresenter(useCase: NewTransactionUseCase) = NewTransactionPresenter(useCase)

}