package com.shagalalab.qarejet.domain.interactor.transaction

import com.shagalalab.qarejet.domain.interactor.type.SingleUseCase
import com.shagalalab.qarejet.domain.model.Transaction
import com.shagalalab.qarejet.domain.repository.TransactionRepository

class GetAllTransactionsUseCase constructor(
    private val transactionRepository: TransactionRepository
) : SingleUseCase<List<Transaction>> {

    override fun execute() = transactionRepository.getAllTransactions()

}