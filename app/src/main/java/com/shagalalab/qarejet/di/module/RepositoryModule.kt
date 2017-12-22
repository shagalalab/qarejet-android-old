package com.shagalalab.qarejet.di.module

import com.shagalalab.qarejet.data.db.Database
import com.shagalalab.qarejet.data.repository.TransactionRepositoryImpl
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
    fun providesTransactionRepository(database: Database): TransactionRepository = TransactionRepositoryImpl(database)

}