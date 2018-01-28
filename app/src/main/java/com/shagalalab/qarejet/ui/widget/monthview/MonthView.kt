package com.shagalalab.qarejet.ui.widget.monthview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.shagalalab.qarejet.R
import kotlinx.android.synthetic.main.view_months.view.*
import org.joda.time.DateTime

class MonthView : FrameLayout {
    private lateinit var currentDate: DateTime
    private lateinit var months: Array<String>
    private lateinit var listener: MonthListener
    private var currentYear: Int = 0

    constructor(context: Context?) : super(context) { setup() }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) { setup() }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) { setup() }

    private fun setup() {
        val view = LayoutInflater.from(context).inflate(R.layout.view_months, this, false)
        addView(view)
    }

    fun init(currentDate: DateTime, year: Int, listener: MonthListener) {
        this.months = resources.getStringArray(R.array.months)
        this.currentDate = currentDate
        this.currentYear = year
        this.listener = listener

        previousMonth.setOnClickListener { setMonth(Month.PREVIOUS) }
        nextMonth.setOnClickListener { setMonth(Month.NEXT) }
    }

    fun setMonth(type: Month) {
        if (type == Month.PREVIOUS) {
            currentDate = currentDate.minusMonths(1)
        } else if (type == Month.NEXT) {
            currentDate = currentDate.plusMonths(1)
        }
        monthYear.text = months[currentDate.monthOfYear().get() - 1].plus(" ").plus(currentDate.year().get().toString())
        listener.changeMonthText(currentDate)
    }

    interface MonthListener {
        fun changeMonthText(date: DateTime)
    }
}

enum class Month { PREVIOUS, CURRENT, NEXT }