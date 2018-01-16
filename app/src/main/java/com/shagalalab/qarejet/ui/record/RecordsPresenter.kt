package com.shagalalab.qarejet.ui.record

import com.shagalalab.qarejet.domain.interactor.transaction.GetTransactionsByDateUseCase
import com.shagalalab.qarejet.util.SchedulersProvider
import org.joda.time.DateTime

class RecordsPresenter constructor(
    private val getTransactionsByDateUseCase: GetTransactionsByDateUseCase,
    private val schedulersProvider: SchedulersProvider
) {
    private lateinit var view: RecordsView
    private lateinit var currentDate: DateTime
    private var currentYear: Int = 0

    fun init(view: RecordsView, currentDate: DateTime, year: Int) {
        this.view = view
        this.currentDate = currentDate
        this.currentYear = year
    }

    fun requestTransactions(type: Month) {
        if (type == Month.PREVIOUS) {
            currentDate = currentDate.minusMonths(1)
        } else if (type == Month.NEXT) {
            currentDate = currentDate.plusMonths(1)
        }
        view.changeMonthText(currentDate.monthOfYear().get(), if (currentDate.year().get() == currentYear) "" else currentDate.year().get().toString())

        getTransactionsByDateUseCase.execute(currentDate)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
            .subscribe(view::updateTransactions)
    }
}

enum class Month { PREVIOUS, CURRENT, NEXT }