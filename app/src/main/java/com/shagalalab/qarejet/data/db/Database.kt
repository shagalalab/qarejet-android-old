package com.shagalalab.qarejet.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.shagalalab.qarejet.data.db.model.AccountDbModel
import com.shagalalab.qarejet.data.db.model.CategoryDbModel
import com.shagalalab.qarejet.data.db.model.TransactionDbModel

/**
 * Created by atabek on 12/14/2017.
 */

@Database(entities = arrayOf(AccountDbModel::class, CategoryDbModel::class, TransactionDbModel::class), version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract val accountDao: AccountDao
    abstract val categoryDao: CategoryDao
    abstract val transactionDao: TransactionDao
}