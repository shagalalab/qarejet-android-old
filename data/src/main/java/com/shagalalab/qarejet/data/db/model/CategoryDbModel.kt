package com.shagalalab.qarejet.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryDbModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id") val id: Long,
    @ColumnInfo(name = "category_title") val title: String,
    @ColumnInfo(name = "category_icon") val icon: String,
    @ColumnInfo(name = "category_color") val color: String,
    @ColumnInfo(name = "category_type") val type: Int
)