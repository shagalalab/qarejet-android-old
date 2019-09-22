package com.shagalalab.qarejet.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shagalalab.qarejet.data.db.model.AccountDbModel
import com.shagalalab.qarejet.data.db.model.CategoryDbModel
import com.shagalalab.qarejet.data.db.model.TransactionDbModel

@Database(
    entities = [AccountDbModel::class, CategoryDbModel::class, TransactionDbModel::class],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun categoryDao(): CategoryDao
    abstract fun transactionDao(): TransactionDao
}