package com.shagalalab.qarejet.domain.interactor.transaction

import com.shagalalab.qarejet.domain.interactor.type.SingleUseCase
import com.shagalalab.qarejet.domain.model.TotalCash
import com.shagalalab.qarejet.domain.model.Transaction
import com.shagalalab.qarejet.domain.model.TransactionType
import com.shagalalab.qarejet.domain.repository.TransactionRepository
import io.reactivex.Single

class GetTotalCashUseCase(private val transactionRepository: TransactionRepository) : SingleUseCase<TotalCash> {

    override fun execute(): Single<TotalCash> {

        return transactionRepository.getAllTransactions()
            .map { it ->
                var income = 0.0
                var expense = 0.0
                var cash = 0.0
                var card = 0.0
                it.forEach {
                    if (it.type == TransactionType.INCOME.ordinal) {
                        income += it.amount
                        if (it.account.title == "Cash")
                            cash += it.amount
                        if (it.account.title == "Card")
                            card += it.amount
                    } else {
                        expense += it.amount
                        if (it.account.title == "Cash")
                            cash -= it.amount
                        if (it.account.title == "Card")
                            card -= it.amount
                    }
                }
                return@map TotalCash(income, expense, cash, card, it.takeLast(5))
            }
    }

}
