package com.shagalalab.qarejet.domain.model

data class Account(val id: Long, val title: String, val currency: String, val sign: String = "") {
    override fun toString() = title
}