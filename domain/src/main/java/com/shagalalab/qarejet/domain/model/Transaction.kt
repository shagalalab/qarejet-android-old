package com.shagalalab.qarejet.domain.model

import java.util.*

data class Transaction(val id: Long, val type: Int, val date: Date, val accountId: Long,
                       val categoryId: Long, val amount: Double, val memo: String)