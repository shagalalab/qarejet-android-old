package com.shagalalab.qarejet.data.db

import android.arch.persistence.room.*
import com.shagalalab.qarejet.data.db.model.AccountDbModel
import com.shagalalab.qarejet.data.db.model.CategoryDbModel
import com.shagalalab.qarejet.data.db.model.TransactionDbModel
import io.reactivex.Single

@Dao
interface AccountDao {
    @Query("SELECT * from accounts")
    fun getAccounts(): Single<List<AccountDbModel>>

    @Query("SELECT * from accounts where id = :id")
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
    @Query("SELECT * from categories")
    fun getCategories(): Single<List<CategoryDbModel>>

    @Query("SELECT * from categories where id = :id")
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
    @Query("SELECT * from transactions")
    fun getTransactions(): Single<List<TransactionDbModel>>

    @Query("SELECT * from transactions where id = :id")
    fun getTransaction(id: Long): Single<TransactionDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransaction(transaction: TransactionDbModel)

    @Delete
    fun deleteTransaction(transaction: TransactionDbModel)
}