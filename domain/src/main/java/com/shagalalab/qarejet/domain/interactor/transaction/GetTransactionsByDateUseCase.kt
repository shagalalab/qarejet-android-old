package com.shagalalab.qarejet.domain.interactor.transaction

import com.shagalalab.qarejet.domain.interactor.type.SingleUseCaseWithParameters
import com.shagalalab.qarejet.domain.model.Transaction
import com.shagalalab.qarejet.domain.repository.TransactionRepository
import io.reactivex.Observable
import org.joda.time.DateTime

class GetTransactionsByDateUseCase(private val transactionRepository: TransactionRepository)
    : SingleUseCaseWithParameters<DateTime, List<Transaction>> {

    override fun execute(parameter: DateTime) =
        transactionRepository
            .getAllTransactions()
            .toObservable()
            .flatMap { Observable.fromIterable(it) }
            .filter { DateTime(it.date.time).monthOfYear() == parameter.monthOfYear() && DateTime(it.date.time).year() == parameter.year() }
            .toList()
}