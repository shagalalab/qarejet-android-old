package com.shagalalab.qarejet.domain.interactor.transaction

import com.shagalalab.qarejet.domain.interactor.type.SingleUseCase
import com.shagalalab.qarejet.domain.model.TotalCash
import com.shagalalab.qarejet.domain.repository.TransactionRepository
import io.reactivex.Single

class GetTotalCashUseCase(private val transactionRepository: TransactionRepository) :
        SingleUseCase<TotalCash> {
    override fun execute(): Single<TotalCash> {

        return transactionRepository.getAllTransactions()
                .map { it ->
                    var income = 0.0
                    var expense = 0.0
                    for (t in it) {
                        if (t.type == 0)
                            income += t.amount
                        else
                            expense += t.amount
                    }
                    return@map TotalCash(income, expense)
                }
    }

}