package com.shagalalab.qarejet.ui.chart

import com.shagalalab.qarejet.domain.interactor.transaction.GetCategoriesWithAmountUseCase
import com.shagalalab.qarejet.util.SchedulersProvider
import org.joda.time.DateTime

class ChartsPresenter(
    private val getGetCategoriesWithAmountUseCase: GetCategoriesWithAmountUseCase,
    private val schedulersProvider: SchedulersProvider
) {
    lateinit var view: ChartsView

    fun init(view: ChartsView) {
        this.view = view
    }

    fun requestData(date: DateTime) {
        getGetCategoriesWithAmountUseCase.execute(date)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
            .subscribe(view::updateData)
    }
}