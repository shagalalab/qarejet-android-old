package com.shagalalab.qarejet.domain.model

data class Category(val id: Long, val title: String, val type: Int) {
    override fun toString() = title
}