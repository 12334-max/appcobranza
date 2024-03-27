package com.cafi.appcobranza.ui.pages.visits

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cafi.appcobranza.model.Event
import com.cafi.appcobranza.ui.layout.CalendarLayout
import java.time.LocalDate

class Visits {
    private val calendarLayout = CalendarLayout()
    private var n = 1
    companion object{
        private var localDate: LocalDate by mutableStateOf<LocalDate>(LocalDate.now())
        private var events by mutableStateOf(
            listOf(
                (Event(if (localDate.toString() == "2024-03-26") LocalDate.of(2024, 3, 26) else localDate, "Evento1 de ejemplo")),
                (Event(if (localDate.toString() == "2024-03-26") LocalDate.of(2024, 3, 26) else localDate, "Evento2 de ejemplo"))
            )
        )
        private var filterOne: List<Event> by mutableStateOf<List<Event>>(emptyList())
        private var filterOThow: List<Event> by mutableStateOf<List<Event>>(emptyList())
    }

    @Composable
    fun VisitsContent(
        isVisibleCalendar: Boolean,
        isFiltter: Boolean
    ){
        Column {
            when(isVisibleCalendar){
                true -> {
                    calendarLayout.CalendarScreen(events){
                        rs -> localDate = rs
                    }
                }
                else -> {}
            }

            Button(
                onClick = {
                    n += 1
                    addEvent(n)
                }
            ) {
                Text(text = "Agregar evento")
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ){
            when(isFiltter){
                true -> {
                    if (getEventFilterOne().isEmpty()){
                        item {
                            Text(text = "Selecciona una fecha")
                        }
                    }else{
                        items(getEventFilterOne()){rs-> Text(text = "${rs.description}")}
                    }
                }
                false -> {
                    if (getEventFilterThow().isEmpty()){
                        item {
                            Text(text = "Selecciona una fecha")
                        }
                    }else{
                        items(getEventFilterThow()){rs-> Text(text = "${rs.description}")}
                    }
                }
            }

        }

        Log.e("Filter", events.ifEmpty { "Selecciona una fecha" }.toString() , )
        Log.e("FilterLenght", events.toString() , )
    }

    private fun getEventFilterOne(): List<Event> {
        filterOne =  events.filter { it.description == "Evento2 de ejemplo" }
        return filterOne.filter { it.date == localDate }
    }

    private fun getEventFilterThow(): List<Event> {
        filterOThow =  events.filter { it.description == "Evento1 de ejemplo" }
        return filterOThow.filter { it.date == localDate }
    }

    private fun addEvent(n: Int){
        var e: List<Event> = listOf((Event(if (localDate.toString() == "2024-03-26") LocalDate.of(2024, 3, n) else localDate, "Evento1 de ejemplo")),
            (Event(if (localDate.toString() == "2024-03-26") LocalDate.of(2024, 3, n) else localDate, "Evento2 de ejemplo")))

        events = events + e

    }
}


