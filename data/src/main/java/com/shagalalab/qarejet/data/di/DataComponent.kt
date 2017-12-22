package com.shagalalab.qarejet.data.di

import com.shagalalab.qarejet.data.repository.TransactionRepositoryImpl
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(DbModule::class))
interface DataComponent {
    fun inject(transactionRepository: TransactionRepositoryImpl)
}