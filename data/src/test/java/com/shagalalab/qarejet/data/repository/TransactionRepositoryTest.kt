package com.shagalalab.qarejet.data.repository

import com.nhaarman.mockito_kotlin.mock
import com.shagalalab.qarejet.data.db.Database
import com.shagalalab.qarejet.data.db.TransactionDao
import com.shagalalab.qarejet.data.db.model.AccountDbModel
import com.shagalalab.qarejet.data.db.model.CategoryDbModel
import com.shagalalab.qarejet.data.db.model.FullTransactionModel
import com.shagalalab.qarejet.data.db.model.TransactionDbModel
import com.shagalalab.qarejet.domain.model.Account
import com.shagalalab.qarejet.domain.model.Category
import com.shagalalab.qarejet.domain.model.Transaction
import com.shagalalab.qarejet.domain.repository.TransactionRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import java.util.Date
import org.mockito.Mockito.`when` as whenever

class TransactionRepositoryTest {

    private val db = mock<Database>()
    private val date = Date()
    private val accountDbModel1 = AccountDbModel(1, "account1", "currency1")
    private val accountDbModel2 = AccountDbModel(2, "account2", "currency2")
    private val categoryDbModel1 = CategoryDbModel(1, "category1", "icon_one", "color_one", 1)
    private val categoryDbModel2 = CategoryDbModel(2, "category2", "icon_two", "color_two", 2)
    private val transactionDao = mock<TransactionDao>()
    private val transactionDbModel1 = TransactionDbModel(1, 1, date, 1, 1, 1.0, "")
    private val transactionDbModel2 = TransactionDbModel(2, 2, date, 2, 2, 2.0, "")
    private val transactionDbModelList = listOf(FullTransactionModel(transactionDbModel1, categoryDbModel1, accountDbModel1),
        FullTransactionModel(transactionDbModel2, categoryDbModel2, accountDbModel2))

    private val account1 = Account(1, "account1", "currency1")
    private val account2 = Account(2, "account2", "currency2")
    private val category1 = Category(1, "category1", "icon_one", "color_one", 1)
    private val category2 = Category(2, "category2", "icon_two", "color_two", 2)
    private val transaction1 = Transaction(1, 1, date, account1, category1, 1.0, "")
    private val transaction2 = Transaction(2, 2, date, account2, category2, 2.0, "")
    private val transactionList = listOf(transaction1, transaction2)
    private val transactionId = 1L

    private val singleTransactionDbModel = Single.just(FullTransactionModel(transactionDbModel1, categoryDbModel1, accountDbModel1))
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
        test.assertValue({ l -> l == transaction1 })
    }

    @Test
    fun shouldAddTransaction() {
        val test = repository.addTransaction(transaction1).test()
        test.assertNoErrors()
        test.assertComplete()
    }
}