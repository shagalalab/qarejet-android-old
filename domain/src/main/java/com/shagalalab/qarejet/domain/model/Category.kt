package com.shagalalab.qarejet.domain.model

data class Category(val id: Long, val title: String, val icon: Int, val color: Int, val type: Int) {
    override fun toString() = title
}