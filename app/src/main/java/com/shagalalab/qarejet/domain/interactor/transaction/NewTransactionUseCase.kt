package com.shagalalab.qarejet.domain.interactor.transaction

import com.shagalalab.qarejet.domain.interactor.type.SingleUseCase
import com.shagalalab.qarejet.domain.model.Transaction
import com.shagalalab.qarejet.domain.repository.TransactionRepository

/**
 * Created by atabek on 12/14/2017.
 */

class NewTransactionUseCase constructor(
    private val transactionRepository: TransactionRepository
) : SingleUseCase<List<Transaction>> {

    override fun execute() = transactionRepository.getTransactions()

}