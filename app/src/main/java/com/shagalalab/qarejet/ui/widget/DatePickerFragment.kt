package com.shagalalab.qarejet.ui.widget

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import java.util.*

class DatePickerFragment : DialogFragment() {
    lateinit var listener: DatePickerDialog.OnDateSetListener

    fun setDateListener(dateListener: DatePickerDialog.OnDateSetListener) {
        listener = dateListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(context, listener, year, month, day)
    }
}