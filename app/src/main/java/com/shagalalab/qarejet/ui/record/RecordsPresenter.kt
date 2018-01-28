package com.shagalalab.qarejet.ui.record

import com.shagalalab.qarejet.domain.interactor.transaction.GetTransactionsByDateUseCase
import com.shagalalab.qarejet.util.SchedulersProvider
import org.joda.time.DateTime

class RecordsPresenter(
    private val getTransactionsByDateUseCase: GetTransactionsByDateUseCase,
    private val schedulersProvider: SchedulersProvider
) {
    private lateinit var view: RecordsView

    fun init(view: RecordsView) {
        this.view = view
    }

    fun requestData(date: DateTime) {
        getTransactionsByDateUseCase.execute(date)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
            .subscribe(view::updateTransactions)
    }
}