package com.shagalalab.qarejet.data.db.model

import android.arch.persistence.room.Embedded

class FullTransactionModel {
    @Embedded lateinit var transaction: TransactionDbModel
    @Embedded lateinit var category: CategoryDbModel
    @Embedded lateinit var account: AccountDbModel

    constructor()
    constructor(transaction: TransactionDbModel, category: CategoryDbModel, account: AccountDbModel) {
        this.transaction = transaction
        this.category = category
        this.account = account
    }

}