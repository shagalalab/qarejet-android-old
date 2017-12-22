package com.shagalalab.qarejet.domain.repository

import com.shagalalab.qarejet.domain.model.Transaction
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by atabek on 12/14/2017.
 */

interface TransactionRepository {

    fun getAllTransactions(): Single<List<Transaction>>
    fun getTransaction(id: Long): Single<Transaction>
    fun addTransaction(transaction: Transaction): Completable
}