package com.shagalalab.qarejet.domain.model

import java.io.Serializable

data class Category(val id: Long, val title: String, val icon: String, val color: String, val type: Int) : Serializable {
    override fun toString() = title
}