package com.shagalalab.qarejet.ui.chart

import com.shagalalab.qarejet.domain.interactor.transaction.GetCategoriesWithAmountUseCase
import com.shagalalab.qarejet.domain.model.Category
import com.shagalalab.qarejet.util.SchedulersProvider
import com.shagalalab.qarejet.util.Screens
import org.joda.time.DateTime
import ru.terrakok.cicerone.Router

class ChartsPresenter(
    private val router: Router,
    private val getCategoriesWithAmountUseCase: GetCategoriesWithAmountUseCase,
    private val schedulersProvider: SchedulersProvider
) {
    lateinit var view: ChartsView

    fun init(view: ChartsView) {
        this.view = view
    }

    fun requestData(date: DateTime, transactionType: Int) {
        getCategoriesWithAmountUseCase.execute(Pair(date, transactionType))
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
            .subscribe(view::updateData)
    }

    fun handleChartItemClick(category: Category, currentDate: DateTime) {
        router.navigateTo(Screens.getCategoryScreen(Pair(category, currentDate)))
    }
}