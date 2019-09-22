package com.shagalalab.qarejet.core.utils

data class Category(
    val id: Long,
    val title: String,
    val icon: String,
    val color: String,
    val type: Int
) {
    override fun toString() = title
}