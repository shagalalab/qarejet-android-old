package com.shagalalab.qarejet.data.db.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "accounts")
data class AccountDbModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "account_id") val id: Long,
    @ColumnInfo(name = "account_title") val title: String,
    @ColumnInfo(name = "account_currency") val currency: String,
    @ColumnInfo(name = "account_sign") val sign: String = ""
)