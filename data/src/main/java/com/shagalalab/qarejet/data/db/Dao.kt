package com.shagalalab.qarejet.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shagalalab.qarejet.data.db.model.AccountDbModel
import com.shagalalab.qarejet.data.db.model.CategoryDbModel
import com.shagalalab.qarejet.data.db.model.FullTransactionModel
import com.shagalalab.qarejet.data.db.model.TransactionDbModel
import io.reactivex.Single

@Dao
interface AccountDao {
    @Query("SELECT * FROM accounts")
    fun getAccounts(): Single<List<AccountDbModel>>

    @Query("SELECT * FROM accounts WHERE account_id = :id")
    fun getAccount(id: Long): Single<AccountDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAccount(account: AccountDbModel)

    @Insert
    fun insertAccounts(accounts: List<AccountDbModel>)

    @Delete
    fun deleteAccount(account: AccountDbModel)
}

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    fun getCategories(): Single<List<CategoryDbModel>>

    @Query("SELECT * FROM categories WHERE category_id = :id")
    fun getCategory(id: Long): Single<CategoryDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: CategoryDbModel)

    @Insert
    fun insertCategories(categories: List<CategoryDbModel>)

    @Delete
    fun deleteCategory(category: CategoryDbModel)
}

@Dao
interface TransactionDao {
    @Query("SELECT transactions.*, accounts.*, categories.*\n" +
        "FROM transactions\n" +
        "INNER JOIN accounts ON transactions.acc_id = accounts.account_id\n" +
        "INNER JOIN categories ON transactions.cat_id = categories.category_id")
    fun getTransactions(): Single<List<FullTransactionModel>>

    @Query("SELECT transactions.*, accounts.*, categories.*\n" +
        "FROM transactions\n" +
        "INNER JOIN accounts ON transactions.acc_id = accounts.account_id\n" +
        "INNER JOIN categories ON transactions.cat_id = categories.category_id\n" +
        "WHERE date > :from AND date < :to AND categories.category_id = :categoryId")
    fun getTransactionsWithinDateByCategory(from: Long, to: Long, categoryId: Long): Single<List<FullTransactionModel>>

    @Query("SELECT transactions.*, accounts.*, categories.*\n" +
        "FROM transactions\n" +
        "INNER JOIN accounts ON transactions.acc_id = accounts.account_id\n" +
        "INNER JOIN categories ON transactions.cat_id = categories.category_id\n" +
        "WHERE date > :from AND date < :to AND categories.category_type = :categoryType")
    fun getTransactionsWithinDateByType(from: Long, to: Long, categoryType: Int): Single<List<FullTransactionModel>>

    @Query("SELECT transactions.*, accounts.*, categories.*\n" +
        "FROM transactions\n" +
        "INNER JOIN accounts ON transactions.acc_id = accounts.account_id\n" +
        "INNER JOIN categories ON transactions.cat_id = categories.category_id\n" +
        "WHERE id = :id")
    fun getTransaction(id: Long): Single<FullTransactionModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransaction(transaction: TransactionDbModel)

    @Insert
    fun insertTransactions(transactions: List<TransactionDbModel>)

    @Delete
    fun deleteTransaction(transaction: TransactionDbModel)
}