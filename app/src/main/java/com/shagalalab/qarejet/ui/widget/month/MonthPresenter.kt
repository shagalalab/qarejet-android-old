package com.shagalalab.qarejet.ui.widget.month

import org.joda.time.DateTime

class MonthPresenter(
    private val months: Array<String>,
    private var currentDate: DateTime,
    private val monthListener: MonthListener,
    private val internalListener: MonthView.Listener
) {

    fun switchToPrev() {
        setMonth(Month.PREVIOUS)
    }

    fun switchToNext() {
        setMonth(Month.NEXT)
    }

    fun switchToCurrent() {
        setMonth(Month.CURRENT)
    }

    private fun setMonth(type: Month) {
        if (type == Month.PREVIOUS) {
            currentDate = currentDate.minusMonths(1)
        } else if (type == Month.NEXT) {
            currentDate = currentDate.plusMonths(1)
        }
        val monthYearText = months[currentDate.monthOfYear - 1].plus(" ").plus(currentDate.year.toString())
        monthListener.onMonthChanged(currentDate)
        internalListener.onMonthTextChanged(monthYearText)
    }
}

enum class Month { PREVIOUS, CURRENT, NEXT }