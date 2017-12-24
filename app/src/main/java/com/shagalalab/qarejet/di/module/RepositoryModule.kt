package com.shagalalab.qarejet.di.module

import android.content.SharedPreferences
import com.shagalalab.qarejet.data.db.Database
import com.shagalalab.qarejet.data.repository.AccountRepositoryImpl
import com.shagalalab.qarejet.data.repository.CategoryRepositoryImpl
import com.shagalalab.qarejet.data.repository.ConfigRepositoryImpl
import com.shagalalab.qarejet.data.repository.TransactionRepositoryImpl
import com.shagalalab.qarejet.domain.repository.AccountRepository
import com.shagalalab.qarejet.domain.repository.CategoryRepository
import com.shagalalab.qarejet.domain.repository.ConfigRepository
import com.shagalalab.qarejet.domain.repository.TransactionRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by atabek on 12/16/2017.
 */

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesTransactionRepository(database : Database): TransactionRepository = TransactionRepositoryImpl(database)

    @Provides
    @Singleton
    fun providesAccountRepository(database: Database): AccountRepository = AccountRepositoryImpl(database)

    @Provides
    @Singleton
    fun providesCategoryRepository(database: Database): CategoryRepository = CategoryRepositoryImpl(database)

    @Provides
    @Singleton
    fun providesConfigRepository(sharedPreferences: SharedPreferences): ConfigRepository = ConfigRepositoryImpl(sharedPreferences)

}