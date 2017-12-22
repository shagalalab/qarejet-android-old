package com.shagalalab.qarejet.domain.model

import java.util.*

/**
 * Created by atabek on 12/10/2017.
 */

data class Transaction(val id: Long, val type: String, val date: Date, val accountId: Long,
                       val categoryId: Long, val amount: Double, val memo: String)