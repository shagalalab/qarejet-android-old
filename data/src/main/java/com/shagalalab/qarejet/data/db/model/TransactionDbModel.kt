package com.shagalalab.qarejet.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
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