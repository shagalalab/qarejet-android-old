package com.shagalalab.qarejet.di.module

import com.shagalalab.qarejet.domain.interactor.transaction.AddNewTransactionUseCase
import com.shagalalab.qarejet.domain.interactor.transaction.GetAllTransactionsUseCase
import com.shagalalab.qarejet.domain.interactor.transaction.GetTransactionUseCase
import com.shagalalab.qarejet.domain.repository.TransactionRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by atabek on 12/16/2017.
 */

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun providesNewTransactionUseCase(repository: TransactionRepository) = AddNewTransactionUseCase(repository)

    @Provides
    @Singleton
    fun providesGetAllTransactionsUseCase(repository: TransactionRepository) = GetAllTransactionsUseCase(repository)

    @Provides
    @Singleton
    fun providesGetTransactionUseCase(repository: TransactionRepository) = GetTransactionUseCase(repository)

}