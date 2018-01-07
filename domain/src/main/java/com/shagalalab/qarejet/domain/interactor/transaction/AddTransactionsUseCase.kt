package com.shagalalab.qarejet.domain.interactor.transaction

import com.shagalalab.qarejet.domain.interactor.type.CompletableUseCaseWithParameters
import com.shagalalab.qarejet.domain.model.Transaction
import com.shagalalab.qarejet.domain.repository.TransactionRepository
import io.reactivex.Completable

class AddTransactionsUseCase constructor(
        private val transactionRepository: TransactionRepository
) : CompletableUseCaseWithParameters<List<Transaction>> {

    override fun execute(parameter: List<Transaction>): Completable = transactionRepository.addTransactions(parameter)

}