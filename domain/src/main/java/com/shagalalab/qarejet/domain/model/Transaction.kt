package com.shagalalab.qarejet.domain.model

import java.util.Date

data class Transaction(val id: Long, val type: Int, val date: Date, val account: Account,
                       val category: Category, val amount: Double, val memo: String)

enum class TransactionType {
    INCOME,
    EXPENSE
}
