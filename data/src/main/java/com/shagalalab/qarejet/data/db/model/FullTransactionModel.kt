package com.shagalalab.qarejet.data.db.model

import androidx.room.Embedded

class FullTransactionModel {
    @Embedded lateinit var transaction: TransactionDbModel
    @Embedded lateinit var category: CategoryDbModel
    @Embedded lateinit var account: AccountDbModel
}