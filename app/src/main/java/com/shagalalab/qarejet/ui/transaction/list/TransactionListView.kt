package com.shagalalab.qarejet.ui.transaction.list

import com.shagalalab.qarejet.domain.model.Transaction

interface TransactionListView {
    fun updateTransactions(transactions: List<Transaction>)
}