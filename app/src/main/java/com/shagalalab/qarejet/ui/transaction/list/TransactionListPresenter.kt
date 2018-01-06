package com.shagalalab.qarejet.ui.transaction.list

import com.shagalalab.qarejet.domain.interactor.transaction.GetAllTransactionsUseCase
import com.shagalalab.qarejet.util.SchedulersProvider

class TransactionListPresenter constructor(
    private val getAllTransactionsUseCase: GetAllTransactionsUseCase,
    private val schedulersProvider: SchedulersProvider
) {
    private lateinit var view: TransactionListView

    fun init(view: TransactionListView) {
        this.view = view
    }

    fun requestAllTransactions() {
        getAllTransactionsUseCase.execute()
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
            .subscribe(view::updateTransactions)
    }
}