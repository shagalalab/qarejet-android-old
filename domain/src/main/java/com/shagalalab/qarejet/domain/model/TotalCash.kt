package com.shagalalab.qarejet.domain.model

data class TotalCash(val incomeAmount: Double, val expenseAmount: Double, val cashAmount: Double, val cardAmount: Double, val lastTransactions: List<Transaction>)