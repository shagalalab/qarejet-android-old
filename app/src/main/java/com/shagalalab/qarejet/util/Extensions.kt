package com.shagalalab.qarejet.util

import java.text.DateFormat
import java.util.*

fun Calendar.toShortDate(): String {
    return DateFormat.getDateInstance(DateFormat.SHORT).format(this.time)
}

fun Calendar.toShortTime(): String {
    return DateFormat.getTimeInstance(DateFormat.SHORT).format(this.time)
}

fun Calendar.isToday(): Boolean {
    val now = Calendar.getInstance()
    return this.get(Calendar.YEAR) == now.get(Calendar.YEAR)
            && this.get(Calendar.MONTH) == now.get(Calendar.MONTH)
            && this.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH)
}