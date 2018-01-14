package com.shagalalab.qarejet.ui.record

import com.shagalalab.qarejet.domain.model.Transaction

interface RecordsView {
    fun updateTransactions(transactions: List<Transaction>)
    fun changeMonthText(month: Int, year: String)
}