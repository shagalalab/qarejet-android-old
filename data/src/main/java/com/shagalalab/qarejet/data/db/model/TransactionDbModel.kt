package com.shagalalab.qarejet.data.db.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.shagalalab.qarejet.data.db.DateConverter
import java.util.*

/**
 * Created by atabek on 12/14/2017.
 */

@Entity(tableName = "transactions"
//        foreignKeys = arrayOf(
//                ForeignKey(
//                        entity = AccountDbModel::class,
//                        parentColumns = arrayOf("id"),
//                        childColumns = arrayOf("account_id")),
//                ForeignKey(
//                        entity = CategoryDbModel::class,
//                        parentColumns = arrayOf("id"),
//                        childColumns = arrayOf("category_id")
//                ))
)
@TypeConverters(DateConverter::class)
data class TransactionDbModel(
        @PrimaryKey(autoGenerate = true) val id: Long,
        val type: Int,
        val date: Date,
        @ColumnInfo(name = "account_id") val accountId: Long,
        @ColumnInfo(name = "category_id") val categoryId: Long,
        val amount: Double,
        val memo: String
)