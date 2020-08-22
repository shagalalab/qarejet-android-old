package com.shagalalab.qarejet.core.utils

import java.io.Serializable

data class Category(
    val id: Long,
    val title: String,
    val icon: String,
    val color: String,
    val type: Int
) : Serializable {
    override fun toString() = title
}