package com.shagalalab.qarejet.data.db.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryDbModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id") val id: Long,
    @ColumnInfo(name = "category_title") val title: String,
    @ColumnInfo(name = "category_icon") val icon: Int,
    @ColumnInfo(name = "category_color") val color: Int,
    @ColumnInfo(name = "category_type") val type: Int
)