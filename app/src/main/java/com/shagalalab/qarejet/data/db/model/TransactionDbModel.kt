package com.shagalalab.qarejet.data.db.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Created by atabek on 12/14/2017.
 */

@Entity(tableName = "transactions")
data class TransactionDbModel(
        @PrimaryKey val id: Int, val type: String, val date: Date, val account: AccountDbModel,
        val category: CategoryDbModel, val amount: Double, val memo: String
)