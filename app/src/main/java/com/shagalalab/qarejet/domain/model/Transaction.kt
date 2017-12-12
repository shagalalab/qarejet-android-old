package com.shagalalab.qarejet.domain.model

import java.util.*

/**
 * Created by atabek on 12/10/2017.
 */

data class Transaction(val id: Int, val type: String, val date: Date, val account: Account,
                       val category: Category, val amount: Double, val memo: String)