package com.shagalalab.qarejet.util

import com.shagalalab.qarejet.domain.model.Account
import com.shagalalab.qarejet.domain.model.Transaction
import java.text.DateFormat
import java.util.Calendar

fun Calendar.toShortDate(): String {
    return DateFormat.getDateInstance(DateFormat.SHORT).format(this.time)
}

fun Calendar.toShortTime(): String {
    return DateFormat.getTimeInstance(DateFormat.SHORT).format(this.time)
}

fun Calendar.toShortDateTime(): String {
    return this.toShortDate().plus(", ").plus(this.toShortTime())
}

fun Calendar.isToday(): Boolean {
    val now = Calendar.getInstance()
    return this.get(Calendar.YEAR) == now.get(Calendar.YEAR)
            && this.get(Calendar.MONTH) == now.get(Calendar.MONTH)
            && this.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH)
}

fun Transaction.getSign(): String {
    return if (this.isIncome()) "+" else "-"
}

fun Transaction.isIncome(): Boolean {
    return this.type == Constants.TRANSACTION_TYPE_INCOME
}

fun Account.getCurrencySign(): String {
    return if (!this.sign.isEmpty()) this.sign else this.currency
}