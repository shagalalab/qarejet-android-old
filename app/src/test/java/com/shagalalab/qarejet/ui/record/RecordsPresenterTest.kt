package com.shagalalab.qarejet.ui.record

import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.shagalalab.qarejet.TestSchedulers
import com.shagalalab.qarejet.domain.interactor.transaction.GetTransactionsByDateUseCase
import com.shagalalab.qarejet.domain.model.Transaction
import io.reactivex.Single
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RecordsPresenterTest {

    @Mock private lateinit var view: RecordsView
    @Mock private lateinit var getTransactionsByDateUseCase: GetTransactionsByDateUseCase
    @Mock private lateinit var dateTime: DateTime
    @Mock private lateinit var transaction: Transaction

    private val schedulersProvider = TestSchedulers()
    private lateinit var presenter: RecordsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = RecordsPresenter(getTransactionsByDateUseCase, schedulersProvider)
        presenter.init(view)
    }

    @Test
    fun shouldRequestTransactionsForCurrentMonth() {
        whenever(getTransactionsByDateUseCase.execute(dateTime)).thenReturn(Single.just(listOf(transaction)))
        presenter.requestData(dateTime)
        verify(view, times(1)).updateTransactions(listOf(transaction))
    }
}