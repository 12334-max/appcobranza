package com.cafi.appcobranza.utils

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.util.Log
import android.widget.DatePicker
import java.util.Date

class SelectorDate {
    fun openCalendar(context: Context, result: (String)->Unit){
        val year: Int
        val month: Int
        val day: Int

        val calendar = Calendar.getInstance()

        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
        day = calendar.get(Calendar.DAY_OF_MONTH)

        calendar.time = Date()

        Log.d("DatePickerDialog", "DatePickerDialog")
        DatePickerDialog(
            context,
            { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                val date = "$mYear/${mMonth+1}/$mDayOfMonth"
                result(date)
                Log.d("DatePicker", "DatePicker")
            }, year, month, day
        ).show()
    }
}