package com.cafi.appcobranza.utils

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.icu.util.LocaleData
import android.util.Log
import android.widget.DatePicker
import java.time.LocalDate
import java.util.Date

class SelectorDate {
    fun openCalendar(context: Context, result: (LocalDate)->Unit){
        val year: Int
        val month: Int
        val day: Int

        val calendar = Calendar.getInstance()
        var date: LocalDate
        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
        day = calendar.get(Calendar.DAY_OF_MONTH)

        calendar.time = Date()

        Log.d("DatePickerDialog", "DatePickerDialog")
        DatePickerDialog(
            context,
            { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                date = LocalDate.of(mYear,mMonth+1, mDayOfMonth)
                result(date)
                Log.d("DatePicker", "DatePicker")
            }, year, month, day
        ).show()
    }
}