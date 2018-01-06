package com.shagalalab.qarejet.data.db.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.shagalalab.qarejet.data.db.DateConverter
import java.util.Date

@Entity(tableName = "transactions")
@TypeConverters(DateConverter::class)
data class TransactionDbModel(
        @PrimaryKey(autoGenerate = true) val id: Long,
        val type: Int,
        val date: Date,
        @ColumnInfo(name = "acc_id") val accountId: Long,
        @ColumnInfo(name = "cat_id") val categoryId: Long,
        val amount: Double,
        val memo: String
)