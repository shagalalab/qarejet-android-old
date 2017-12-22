package com.shagalalab.qarejet.data.repository

import android.content.Context
import com.shagalalab.qarejet.data.db.Database
import com.shagalalab.qarejet.data.db.DbToDomainMapper
import com.shagalalab.qarejet.data.db.DomainToDbMapper
import com.shagalalab.qarejet.data.di.DaggerDataComponent
import com.shagalalab.qarejet.data.di.DbModule
import com.shagalalab.qarejet.domain.model.Transaction
import com.shagalalab.qarejet.domain.repository.TransactionRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by atabek on 12/14/2017.
 */

class TransactionRepositoryImpl (var context: Context) : TransactionRepository {
    @Inject lateinit var database : Database

    init {
        DaggerDataComponent
                .builder()
                .dbModule(DbModule(context))
                .build()
                .inject(this)
    }


    override fun getTransaction(id: Long): Single<Transaction> {
        return database
                .transactionDao
                .getTransaction(id)
                .map(DbToDomainMapper::mapTransaction)
    }

    override fun getAllTransactions(): Single<List<Transaction>> {
        return database
                .transactionDao
                .getTransactions()
                .map(DbToDomainMapper::mapTransactionList)
    }

    override fun addTransaction(transaction: Transaction): Completable {
        return Completable.fromAction({
            database
                    .transactionDao
                    .insertTransaction(DomainToDbMapper.mapTransaction(transaction))
        })
    }
}