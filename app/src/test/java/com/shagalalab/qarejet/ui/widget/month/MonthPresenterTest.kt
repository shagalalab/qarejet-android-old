package com.shagalalab.qarejet.ui.widget.month

import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MonthPresenterTest {
    @Mock private lateinit var monthListener: MonthListener
    @Mock private lateinit var internalListener: MonthView.Listener

    private lateinit var presenter: MonthPresenter

    private val months = arrayOf("January", "February", "March", "April", "May", "June", "July", "August",
        "September", "October", "November", "December")
    private val dateTime = DateTime.now()
    private val currentYear = dateTime.year

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MonthPresenter(months, dateTime, monthListener, internalListener)
    }

    @Test
    fun switchToPrev() {
        presenter.switchToPrev()
        verifyDateChanged(dateTime.minusMonths(1))
    }

    @Test
    fun switchToNext() {
        presenter.switchToNext()
        verifyDateChanged(dateTime.plusMonths(1))
    }

    @Test
    fun switchToCurrent() {
        presenter.switchToCurrent()
        verifyDateChanged(dateTime)
    }

    private fun verifyDateChanged(modifiedDate: DateTime) {
        verify(monthListener, times(1)).onMonthChanged(modifiedDate)

        var monthYearText = months[modifiedDate.monthOfYear - 1]
        if (modifiedDate.year != currentYear) {
            monthYearText += " " + modifiedDate.year.toString()
        }

        verify(internalListener, times(1)).onMonthTextChanged(monthYearText)
    }
}