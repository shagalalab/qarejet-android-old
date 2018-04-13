package com.shagalalab.qarejet.ui.category

import com.shagalalab.qarejet.domain.interactor.transaction.GetTransactionsByCategoryUseCase
import com.shagalalab.qarejet.util.SchedulersProvider
import org.joda.time.DateTime

class CategoryPresenter(
    private val getTransactionsByCategoryUseCase: GetTransactionsByCategoryUseCase,
    private val schedulersProvider: SchedulersProvider
) {
    lateinit var view: CategoryView

    fun init(view: CategoryView) {
        this.view = view
    }

    fun requestData(date: DateTime, categoryId: Long) {
        getTransactionsByCategoryUseCase.execute(Pair(date, categoryId))
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
            .subscribe(view::updateData)
    }
}