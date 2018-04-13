package com.shagalalab.qarejet.domain.interactor.transaction

import com.shagalalab.qarejet.domain.interactor.type.SingleUseCaseWithParameters
import com.shagalalab.qarejet.domain.model.CategoryWithAmount
import com.shagalalab.qarejet.domain.model.Transaction
import com.shagalalab.qarejet.domain.repository.TransactionRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import org.joda.time.DateTime

class GetTransactionsByCategoryUseCase(private val transactionRepository: TransactionRepository)
    : SingleUseCaseWithParameters<Pair<DateTime, Long>, Pair<List<Transaction>, List<CategoryWithAmount>>> {

    override fun execute(parameter: Pair<DateTime, Long>): Single<Pair<List<Transaction>, List<CategoryWithAmount>>> {
        val startTime = parameter.first.minusMonths(8).dayOfMonth().withMinimumValue().withTimeAtStartOfDay().millis
        val endTime = parameter.first.dayOfMonth().withMaximumValue().plusDays( 1).withTimeAtStartOfDay().minusSeconds(1).millis
        val startCurrentMonthTime = parameter.first.dayOfMonth().withMinimumValue().withTimeAtStartOfDay().millis
        val categoryId = parameter.second

        val transactions = transactionRepository
            .getTransactionsWithinDateByCategory(startCurrentMonthTime, endTime, categoryId)

        val categoriesWithAmount = transactionRepository
            .getTransactionsWithinDateByCategory(startTime, endTime, categoryId)
            .map {
                val categoriesList = hashMapOf<Long, CategoryWithAmount>()

                for (t in it) {
                    if (categoriesList.containsKey<Long>(t.id)) {
                        categoriesList[t.id]?.increaseAmount(t.amount)
                    } else {
                        categoriesList[t.id] = CategoryWithAmount(t.type, t.category, t.amount, t.date.time)
                    }
                }
                return@map categoriesList.values.toList()
            }

        return Single.zip(transactions, categoriesWithAmount, BiFunction { t1, t2 -> Pair(t1, t2) })
    }
}