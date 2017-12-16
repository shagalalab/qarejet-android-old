package com.shagalalab.qarejet.di.module

import com.shagalalab.qarejet.data.TransactionRepositoryImpl
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
    fun providesTransactionRepository(): TransactionRepository = TransactionRepositoryImpl()

}