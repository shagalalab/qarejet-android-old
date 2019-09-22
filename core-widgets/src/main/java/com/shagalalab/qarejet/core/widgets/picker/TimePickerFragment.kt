package com.shagalalab.qarejet.core.widgets.picker

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class TimePickerFragment : DialogFragment() {
    private lateinit var listener: TimePickerDialog.OnTimeSetListener
    private lateinit var calendar: Calendar

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