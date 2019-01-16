package com.shagalalab.qarejet.domain.interactor.transaction

import com.shagalalab.qarejet.domain.interactor.type.SingleUseCase
import com.shagalalab.qarejet.domain.model.AccountWithAmount
import com.shagalalab.qarejet.domain.model.TotalCash
import com.shagalalab.qarejet.domain.model.TransactionType
import com.shagalalab.qarejet.domain.repository.TransactionRepository
import io.reactivex.Single

class GetTotalCashUseCase(private val transactionRepository: TransactionRepository) : SingleUseCase<TotalCash> {

    override fun execute(): Single<TotalCash> {

        return transactionRepository.getAllTransactions()
            .map { it ->
                var income = 0.0
                var expense = 0.0
                val accountsList = hashMapOf<Long, AccountWithAmount>()
                it.forEach {
                    if (accountsList.containsKey<Long>(it.account.id)) {
                        if (it.type == TransactionType.INCOME.ordinal) {
                            income += it.amount
                            accountsList[it.account.id]?.increaseAmount(it.amount)

                        } else {
                            expense += it.amount
                            accountsList[it.account.id]?.decreaseAmount(it.amount)
                        }
                    } else {
                        if (it.type == TransactionType.INCOME.ordinal) {
                            income += it.amount
                        } else {
                            expense += it.amount
                        }
                        accountsList[it.account.id] = AccountWithAmount(it.account.title, it.amount, it.account.currency)
                    }
                }
                return@map TotalCash(income, expense, accountsList.values.toList(), it.takeLast(5))
            }
    }
}
