package com.shagalalab.qarejet.data.repository

import com.nhaarman.mockito_kotlin.mock
import com.shagalalab.qarejet.data.db.AccountDao
import com.shagalalab.qarejet.data.db.Database
import com.shagalalab.qarejet.data.db.model.AccountDbModel
import com.shagalalab.qarejet.domain.model.Account
import com.shagalalab.qarejet.domain.repository.AccountRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when` as whenever

class AccountRepositoryTest {

    private val db = mock<Database>()
    private val accountDao = mock<AccountDao>()
    private val accountDbModel = AccountDbModel(1, "one", "currency1")
    private val accountDbModelList = listOf(accountDbModel, AccountDbModel(2, "two", "currency2"))
    private val account = Account(1, "one", "currency1")
    private val accountList = listOf(account, Account(2, "two", "currency2"))
    private val accountId = 1L
    private val singleAccountDbModel = Single.just(accountDbModel)
    private val singleAccountDbModelList = Single.just(accountDbModelList)
    private lateinit var repository : AccountRepository

    @Before
    fun setUp() {
        repository = AccountRepositoryImpl(db)
        whenever(db.accountDao).thenReturn(accountDao)
    }

    @Test
    fun shouldGetAllAccounts() {
        whenever(accountDao.getAccounts()).thenReturn(singleAccountDbModelList)
        val test = repository.getAllAccounts().test()
        test.assertNoErrors()
        test.assertValue({ l -> l == accountList })
    }

    @Test
    fun shouldGetAccount() {
        whenever(accountDao.getAccount(accountId)).thenReturn(singleAccountDbModel)
        val test = repository.getAccount(accountId).test()
        test.assertNoErrors()
        test.assertValue({ l -> l == account })
    }

    @Test
    fun shouldAddAccount() {
        val test = repository.addAccount(account).test()
        test.assertNoErrors()
        test.assertComplete()
    }

    @Test
    fun shouldAddAccountList() {
        val test = repository.addAccounts(accountList).test()
        test.assertNoErrors()
        test.assertComplete()
    }
}