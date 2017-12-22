package com.shagalalab.qarejet.domain.interactor.transaction

import com.shagalalab.qarejet.domain.interactor.type.CompletableUseCaseWithParameters
import com.shagalalab.qarejet.domain.model.Transaction
import com.shagalalab.qarejet.domain.repository.TransactionRepository
import io.reactivex.Completable

/**
 * Created by atabek on 12/14/2017.
 */

class AddNewTransactionUseCase constructor(
    private val transactionRepository: TransactionRepository
) : CompletableUseCaseWithParameters<Transaction> {

    override fun execute(parameter: Transaction): Completable = transactionRepository.addTransaction(parameter)

}