package com.shagalalab.qarejet.core.widgets.month

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.shagalalab.qarejet.core.widgets.R
import kotlinx.android.synthetic.main.view_months.view.*
import org.joda.time.DateTime

class MonthView : FrameLayout {
    constructor(context: Context) : super(context) { setup() }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) { setup() }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) { setup() }

    lateinit var presenter: MonthPresenter

    private fun setup() {
        val view = LayoutInflater.from(context).inflate(R.layout.view_months, this, false)
        addView(view)
    }

    fun init(currentDate: DateTime, listener: MonthListener) {
        presenter = MonthPresenter(
            resources.getStringArray(R.array.months),
            currentDate,
            listener,
            object : Listener {
                override fun onMonthTextChanged(date: String) {
                    monthYear.text = date
                }
            }
        )

        previousMonth.setOnClickListener { presenter.switchToPrev() }
        nextMonth.setOnClickListener { presenter.switchToNext() }
    }

    fun setCurrentMonth() {
        presenter.switchToCurrent()
    }

    fun getCurrentDate() = presenter.getCurrentDate()

    interface Listener {
        fun onMonthTextChanged(date: String)
    }
}

interface MonthListener {
    fun onMonthChanged(date: DateTime)
}
