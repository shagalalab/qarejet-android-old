package com.shagalalab.qarejet.di.module

import com.shagalalab.qarejet.domain.interactor.account.AddAccountsUseCase
import com.shagalalab.qarejet.domain.interactor.account.GetAllAccountsUseCase
import com.shagalalab.qarejet.domain.interactor.category.AddCategoriesUseCase
import com.shagalalab.qarejet.domain.interactor.category.GetAllCategoriesUseCase
import com.shagalalab.qarejet.domain.interactor.config.InitialDataUseCase
import com.shagalalab.qarejet.domain.interactor.transaction.AddTransactionUseCase
import com.shagalalab.qarejet.domain.interactor.transaction.GetAllTransactionsUseCase
import com.shagalalab.qarejet.domain.interactor.transaction.GetTransactionUseCase
import com.shagalalab.qarejet.domain.interactor.transaction.GetTransactionsByDateUseCase
import com.shagalalab.qarejet.domain.repository.AccountRepository
import com.shagalalab.qarejet.domain.repository.CategoryRepository
import com.shagalalab.qarejet.domain.repository.ConfigRepository
import com.shagalalab.qarejet.domain.repository.TransactionRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun providesNewTransactionUseCase(repository: TransactionRepository) = AddTransactionUseCase(repository)

    @Provides
    @Singleton
    fun providesGetTransactionsByDateUseCase(repository: TransactionRepository) = GetTransactionsByDateUseCase(repository)

    @Provides
    @Singleton
    fun providesGetAllTransactionsUseCase(repository: TransactionRepository) = GetAllTransactionsUseCase(repository)

    @Provides
    @Singleton
    fun providesGetTransactionUseCase(repository: TransactionRepository) = GetTransactionUseCase(repository)

    @Provides
    @Singleton
    fun providesInitialDataUseCase(repository: ConfigRepository) = InitialDataUseCase(repository)

    @Provides
    @Singleton
    fun providesGetAllCategoriesUseCase(repository: CategoryRepository) = GetAllCategoriesUseCase(repository)

    @Provides
    @Singleton
    fun providesAddCategoriesUseCase(repository: CategoryRepository) = AddCategoriesUseCase(repository)

    @Provides
    @Singleton
    fun providesGetAllAccountsUseCase(repository: AccountRepository) = GetAllAccountsUseCase(repository)

    @Provides
    @Singleton
    fun providesAddAccountsUseCase(repository: AccountRepository) = AddAccountsUseCase(repository)
}