package com.shagalalab.qarejet.data.repository

import com.nhaarman.mockito_kotlin.mock
import com.shagalalab.qarejet.data.db.Database
import com.shagalalab.qarejet.data.db.TransactionDao
import com.shagalalab.qarejet.data.db.model.TransactionDbModel
import com.shagalalab.qarejet.domain.model.Transaction
import com.shagalalab.qarejet.domain.repository.TransactionRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import java.util.*
import org.mockito.Mockito.`when` as whenever

class TransactionRepositoryTest {

    private val db = mock<Database>()
    private val date = Date()
    private val transactionDao = mock<TransactionDao>()
    private val transactionDbModel = TransactionDbModel(1, 1, date, 1, 1, 1.0, "")
    private val transactionDbModelList = listOf(transactionDbModel, TransactionDbModel(2, 2, date, 2, 2, 2.0, ""))
    private val transaction = Transaction(1, 1, date, 1, 1, 1.0, "")
    private val transactionList = listOf(transaction, Transaction(2, 2, date, 2, 2, 2.0, ""))
    private val transactionId = 1L
    private val singleTransactionDbModel = Single.just(transactionDbModel)
    private val singleTransactionListDbModel = Single.just(transactionDbModelList)
    private lateinit var repository : TransactionRepository

    @Before
    fun setUp() {
        repository = TransactionRepositoryImpl(db)
        whenever(db.transactionDao).thenReturn(transactionDao)
    }

    @Test
    fun shouldGetAllTransactions() {
        whenever(transactionDao.getTransactions()).thenReturn(singleTransactionListDbModel)
        val test = repository.getAllTransactions().test()
        test.assertNoErrors()
        test.assertValue({ l -> l == transactionList })
    }

    @Test
    fun shouldGetTransaction() {
        whenever(transactionDao.getTransaction(transactionId)).thenReturn(singleTransactionDbModel)
        val test = repository.getTransaction(transactionId).test()
        test.assertNoErrors()
        test.assertValue({ l -> l == transaction })
    }

    @Test
    fun shouldAddTransaction() {
        val test = repository.addTransaction(transaction).test()
        test.assertNoErrors()
        test.assertComplete()
    }
}