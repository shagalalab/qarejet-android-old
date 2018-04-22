package com.shagalalab.qarejet.domain.interactor.transaction

import com.shagalalab.qarejet.domain.interactor.type.SingleUseCaseWithParameters
import com.shagalalab.qarejet.domain.model.CategoryWithAmount
import com.shagalalab.qarejet.domain.repository.TransactionRepository
import io.reactivex.Single
import org.joda.time.DateTime

class GetCategoriesWithAmountUseCase(private val transactionRepository: TransactionRepository)
    : SingleUseCaseWithParameters<Pair<DateTime, Int>, List<CategoryWithAmount>> {

    override fun execute(parameter: Pair<DateTime, Int>): Single<List<CategoryWithAmount>> {
        val startDate = parameter.first.dayOfMonth().withMinimumValue().withTimeAtStartOfDay().millis
        val endDate = parameter.first.dayOfMonth().withMaximumValue().plusDays(1).withTimeAtStartOfDay().minusSeconds(1).millis
        val categoryType = parameter.second

        return transactionRepository
            .getTransactionsWithinDateByType(startDate, endDate, categoryType)
            .map {
                val categoriesList = hashMapOf<Long, CategoryWithAmount>()

                for (t in it) {
                    if (categoriesList.containsKey<Long>(t.category.id)) {
                        categoriesList[t.category.id]?.increaseAmount(t.amount)
                    } else {
                        categoriesList[t.category.id] = CategoryWithAmount(t.type, t.category, t.amount, t.date.time)
                    }
                }
                return@map categoriesList.values.toList()
            }
    }
}
