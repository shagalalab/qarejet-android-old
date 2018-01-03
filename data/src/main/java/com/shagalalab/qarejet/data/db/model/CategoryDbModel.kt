package com.shagalalab.qarejet.data.db.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryDbModel(@PrimaryKey(autoGenerate = true) val id: Long, val title: String, val type: Int)