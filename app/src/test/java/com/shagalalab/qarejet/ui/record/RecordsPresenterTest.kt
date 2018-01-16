package com.shagalalab.qarejet.ui.record

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.never
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
    @Mock private lateinit var propertyYear: DateTime.Property
    @Mock private lateinit var propertyMonth: DateTime.Property
    @Mock private lateinit var transaction: Transaction
    private val schedulersProvider = TestSchedulers()
    private val year = 2018
    private lateinit var presenter: RecordsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = RecordsPresenter(getTransactionsByDateUseCase, schedulersProvider)
        presenter.init(view, dateTime, year)
    }

    @Test
    fun shouldRequestTransactionsForCurrentMonth() {
        whenever(dateTime.monthOfYear()).thenReturn(propertyMonth)
        whenever(dateTime.year()).thenReturn(propertyYear)
        whenever(propertyMonth.get()).thenReturn(1)
        whenever(propertyYear.get()).thenReturn(year)
        whenever(getTransactionsByDateUseCase.execute(dateTime)).thenReturn(Single.just(listOf(transaction)))

        presenter.requestTransactions(Month.CURRENT)

        verify(dateTime, never()).plusMonths(any())
        verify(dateTime, never()).minusMonths(any())
        verify(view, times(1)).changeMonthText(any(), any())
        verify(view, times(1)).updateTransactions(listOf(transaction))
    }

    @Test
    fun shouldRequestTransactionsForPreviousMonth() {
        whenever(dateTime.monthOfYear()).thenReturn(propertyMonth)
        whenever(dateTime.year()).thenReturn(propertyYear)
        whenever(propertyMonth.get()).thenReturn(1)
        whenever(propertyYear.get()).thenReturn(year)
        whenever(getTransactionsByDateUseCase.execute(dateTime)).thenReturn(Single.just(listOf(transaction)))
        whenever(dateTime.minusMonths(1)).thenReturn(dateTime)

        presenter.requestTransactions(Month.PREVIOUS)

        verify(dateTime, never()).plusMonths(any())
        verify(view, times(1)).changeMonthText(any(), any())
        verify(view, times(1)).updateTransactions(listOf(transaction))
    }

    @Test
    fun shouldRequestTransactionsForNextMonth() {
        whenever(dateTime.monthOfYear()).thenReturn(propertyMonth)
        whenever(dateTime.year()).thenReturn(propertyYear)
        whenever(propertyMonth.get()).thenReturn(1)
        whenever(propertyYear.get()).thenReturn(year)
        whenever(getTransactionsByDateUseCase.execute(dateTime)).thenReturn(Single.just(listOf(transaction)))
        whenever(dateTime.plusMonths(1)).thenReturn(dateTime)

        presenter.requestTransactions(Month.NEXT)

        verify(dateTime, never()).minusMonths(any())
        verify(view, times(1)).changeMonthText(any(), any())
        verify(view, times(1)).updateTransactions(listOf(transaction))
    }
}