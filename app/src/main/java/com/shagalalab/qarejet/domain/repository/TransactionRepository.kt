package com.shagalalab.qarejet.domain.repository

import com.shagalalab.qarejet.domain.model.Transaction
import io.reactivex.Single

/**
 * Created by atabek on 12/14/2017.
 */

interface TransactionRepository {

    fun getTransactions(): Single<List<Transaction>>
    fun getTransaction(id: Int): Single<Transaction>
    fun addTransaction()
}