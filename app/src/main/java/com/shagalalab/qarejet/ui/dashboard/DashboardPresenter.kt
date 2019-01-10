package com.shagalalab.qarejet.ui.dashboard

import com.shagalalab.qarejet.domain.interactor.transaction.GetTotalCashUseCase
import com.shagalalab.qarejet.util.SchedulersProvider

class DashboardPresenter(
        private val getTotalCashUseCase: GetTotalCashUseCase,
        private val schedulersProvider: SchedulersProvider
) {
    private lateinit var view: DashboardView

    fun init(view: DashboardView) {
        this.view = view
    }

    fun requestData() {
        getTotalCashUseCase.execute()
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
                .subscribe(view::setCashFlow)
    }
}