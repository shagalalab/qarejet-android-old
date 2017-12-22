package com.shagalalab.qarejet.domain.interactor.transaction

import com.shagalalab.qarejet.domain.interactor.type.SingleUseCaseWithParameters
import com.shagalalab.qarejet.domain.model.Transaction
import com.shagalalab.qarejet.domain.repository.TransactionRepository
import io.reactivex.Single

/**
 * Created by atabek on 12/14/2017.
 */

class GetTransactionUseCase constructor(
    private val transactionRepository: TransactionRepository
) : SingleUseCaseWithParameters<Long, Transaction> {

    override fun execute(parameter: Long): Single<Transaction> {
        return transactionRepository.getTransaction(parameter)
    }

}