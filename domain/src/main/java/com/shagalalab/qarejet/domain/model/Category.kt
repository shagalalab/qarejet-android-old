package com.shagalalab.qarejet.domain.model

/**
 * Created by atabek on 12/10/2017.
 */

data class Category(val id: Long, val title: String) {
    override fun toString() = title
}