package com.shagalalab.qarejet.domain.model

data class Account(val id: Long, val title: String) {
    override fun toString() = title
}