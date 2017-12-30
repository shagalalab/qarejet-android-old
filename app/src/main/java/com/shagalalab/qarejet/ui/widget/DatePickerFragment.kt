package com.shagalalab.qarejet.ui.widget

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import java.util.*

class DatePickerFragment : DialogFragment() {
    lateinit var listener: DatePickerDialog.OnDateSetListener
    lateinit var calendar: Calendar

    fun init(currentCalendar: Calendar, dateListener: DatePickerDialog.OnDateSetListener) {
        listener = dateListener
        calendar = currentCalendar
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(context, listener, year, month, day)
    }
}