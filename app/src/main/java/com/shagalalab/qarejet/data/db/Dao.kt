package com.shagalalab.qarejet.data.db

import android.arch.persistence.room.*
import com.shagalalab.qarejet.data.db.model.AccountDbModel
import com.shagalalab.qarejet.data.db.model.CategoryDbModel
import com.shagalalab.qarejet.data.db.model.TransactionDbModel
import io.reactivex.Single

/**
 * Created by atabek on 12/14/2017.
 */

@Dao
interface AccountDao {
    @Query("SELECT * from accounts")
    fun getAccounts(): Single<List<AccountDbModel>>

    @Query("SELECT * from account where id = :id")
    fun getAccount(id: Int): Single<AccountDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAccount(account: AccountDbModel)

    @Delete
    fun deleteAccount(account: AccountDbModel)
}

@Dao
interface CategoryDao {
    @Query("SELECT * from categories")
    fun getCategories(): Single<List<CategoryDbModel>>

    @Query("SELECT * from category where id = :id")
    fun getCategory(id: Int): Single<CategoryDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: CategoryDbModel)

    @Delete
    fun deleteCategory(category: CategoryDbModel)
}

@Dao
interface TransactionDao {
    @Query("SELECT * from transactions")
    fun getTransactions(): Single<List<TransactionDbModel>>

    @Query("SELECT * from transaction where id = :id")
    fun getCategory(id: Int): Single<TransactionDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransaction(transaction: TransactionDbModel)

    @Delete
    fun deleteTransaction(transaction: TransactionDbModel)
}