package com.shagalalab.qarejet.data.db

import com.shagalalab.qarejet.data.db.model.AccountDbModel
import com.shagalalab.qarejet.data.db.model.CategoryDbModel
import com.shagalalab.qarejet.data.db.model.FullTransactionModel
import com.shagalalab.qarejet.data.db.model.TransactionDbModel
import com.shagalalab.qarejet.domain.model.Account
import com.shagalalab.qarejet.domain.model.Category
import com.shagalalab.qarejet.domain.model.Transaction
import org.junit.Assert
import org.junit.Test
import java.util.Date

class MappersTest {

    private val date = Date()
    private val account1 = Account(0, "account1")
    private val account2 = Account(0, "account2")
    private val accountList = listOf(account1, account2)
    private val category1 = Category(0, "category1", 1, 1, 1)
    private val category2 = Category(0, "category2", 2, 2, 2)
    private val categoryList = listOf(category1, category2)
    private val transaction1 = Transaction(0, 1, date, account1, category1, 1.0, "transaction1")
    private val transaction2 = Transaction(0, 2, date, account2, category2, 2.0, "transaction2")
    private val transactionList = listOf(transaction1, transaction2)

    private val transactionDbModel1 = TransactionDbModel(0, 1, date, account1.id, category1.id, 1.0, "transaction1")
    private val transactionDbModel2 = TransactionDbModel(0, 2, date, account2.id, category2.id, 2.0, "transaction2")
    private val accountDbModel1 = AccountDbModel(0, "account1")
    private val accountDbModel2 = AccountDbModel(0, "account2")
    private val accountDbModelList = listOf(accountDbModel1, accountDbModel2)
    private val categoryDbModel1 = CategoryDbModel(0, "category1", 1, 1, 1)
    private val categoryDbModel2 = CategoryDbModel(0, "category2", 2, 2, 2)
    private val categoryDbModelList = listOf(categoryDbModel1, categoryDbModel2)
    private val transactionFullModel1 = FullTransactionModel(transactionDbModel1, categoryDbModel1, accountDbModel1)
    private val transactionFullModel2 = FullTransactionModel(transactionDbModel2, categoryDbModel2, accountDbModel2)
    private val transactionFullModelList = listOf(transactionFullModel1, transactionFullModel2)

    // DB -> Domain
    @Test
    fun shouldConvertTransactionFromDbToDomain() {
        Assert.assertEquals(DbToDomainMapper.mapTransaction(transactionFullModel1), transaction1)
    }

    @Test
    fun shouldNotConvertTransactionFromDbToDomain() {
        Assert.assertNotEquals(DbToDomainMapper.mapTransaction(transactionFullModel1), transaction2)
    }

    @Test
    fun shouldConvertTransactionListFromDbToDomain() {
        Assert.assertEquals(DbToDomainMapper.mapTransactionList(transactionFullModelList), transactionList)
    }

    @Test
    fun shouldConvertAccountFromDbToDomain() {
        Assert.assertEquals(DbToDomainMapper.mapAccount(accountDbModel1), account1)
    }

    @Test
    fun shouldNotConvertAccountFromDbToDomain() {
        Assert.assertNotEquals(DbToDomainMapper.mapAccount(accountDbModel1), account2)
    }

    @Test
    fun shouldConvertAccountListFromDbToDomain() {
        Assert.assertEquals(DbToDomainMapper.mapAccountsList(accountDbModelList), accountList)
    }

    @Test
    fun shouldConvertCategoryFromDbToDomain() {
        Assert.assertEquals(DbToDomainMapper.mapCategory(categoryDbModel1), category1)
    }

    @Test
    fun shouldNotConvertCategoryFromDbToDomain() {
        Assert.assertNotEquals(DbToDomainMapper.mapCategory(categoryDbModel1), category2)
    }

    @Test
    fun shouldConvertCategoryListFromDbToDomain() {
        Assert.assertEquals(DbToDomainMapper.mapCategoriesList(categoryDbModelList), categoryList)
    }

    // Domain -> DB
    @Test
    fun shouldConvertTransactionFromDomainToDb() {
        Assert.assertEquals(DomainToDbMapper.mapTransaction(transaction1), transactionDbModel1)
    }

    @Test
    fun shouldNotConvertTransactionFromDomainToDb() {
        Assert.assertNotEquals(DomainToDbMapper.mapTransaction(transaction2), transactionDbModel1)
    }

    @Test
    fun shouldConvertAccountFromDomainToDb() {
        Assert.assertEquals(DomainToDbMapper.mapAccount(account1), accountDbModel1)
    }

    @Test
    fun shouldNotConvertAccountFromDomainToDb() {
        Assert.assertNotEquals(DomainToDbMapper.mapAccount(account2), accountDbModel1)
    }

    @Test
    fun shouldConvertAccountListFromDomainToDb() {
        Assert.assertEquals(DomainToDbMapper.mapAccountsList(accountList), accountDbModelList)
    }

    @Test
    fun shouldConvertCategoryFromDomainToDb() {
        Assert.assertEquals(DomainToDbMapper.mapCategory(category1), categoryDbModel1)
    }

    @Test
    fun shouldNotConvertCategoryFromDomainToDb() {
        Assert.assertNotEquals(DomainToDbMapper.mapCategory(category2), categoryDbModel1)
    }

    @Test
    fun shouldConvertCategoryListFromDomainToDb() {
        Assert.assertEquals(DomainToDbMapper.mapCategoriesList(categoryList), categoryDbModelList)
    }

}