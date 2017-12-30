package com.shagalalab.qarejet.ui.widget

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.format.DateFormat
import java.util.*

class TimePickerFragment : DialogFragment() {
    lateinit var listener: TimePickerDialog.OnTimeSetListener
    lateinit var calendar: Calendar

    fun init(currentCalendar: Calendar, timeListener: TimePickerDialog.OnTimeSetListener) {
        listener = timeListener
        calendar = currentCalendar
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        return TimePickerDialog(context, listener, hour, minute, DateFormat.is24HourFormat(context))
    }
}