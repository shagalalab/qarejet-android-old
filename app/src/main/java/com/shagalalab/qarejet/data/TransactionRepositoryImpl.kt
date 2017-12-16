package com.shagalalab.qarejet.data

import com.shagalalab.qarejet.data.db.Database
import com.shagalalab.qarejet.domain.model.Account
import com.shagalalab.qarejet.domain.model.Category
import com.shagalalab.qarejet.domain.model.Transaction
import com.shagalalab.qarejet.domain.repository.TransactionRepository
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

/**
 * Created by atabek on 12/14/2017.
 */

class TransactionRepositoryImpl: TransactionRepository {
    @Inject lateinit var database: Database

    override fun getTransaction(id: Int): Single<Transaction> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTransactions(): Single<List<Transaction>> {
        val items = listOf(Transaction(12, "sfsf", Date(),
                Account(23, "asfgsfg"), Category(2233, "sfsfg"), 23.32, ""))
        return Single.just(items)
    }

    override fun addTransaction() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}