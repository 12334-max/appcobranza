package com.cafi.appcobranza.ui.pages.visits

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cafi.appcobranza.model.WalletSelected
import com.cafi.appcobranza.ui.layout.CalendarLayout
import com.cafi.appcobranza.ui.layout.MainLayout
import java.time.LocalDate

class Visits {
    private val calendarLayout = CalendarLayout()
    companion object{
        private var localDate: LocalDate by mutableStateOf(LocalDate.now())
        private var wallets: List<WalletSelected> by mutableStateOf(emptyList())
    }

    @Composable
    fun VisitsContent(
        isVisibleCalendar: Boolean,
        isFilterVisit: Boolean,
        events:List<WalletSelected>
    ){
        Column {
            when(isVisibleCalendar){
                true -> {
                    wallets = events
                    calendarLayout.CalendarScreen(wallets){
                        rs -> localDate = rs
                    }
                }
                else -> {
                    Log.e("TAG", events.toString(), )
                }
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ){
            val event = events.filter { it.fechaVisita == localDate }

            items(event){
               r ->
                Text(text = r.wallet.nombreCliente)
                Log.e("TAG", r.wallet.nombreCliente, )
            }

        }

        Log.e("Filter", events.ifEmpty { "La fecha no tiene eventos" }.toString() , )
        Log.e("FilterLenght", events.toString() , )
    }
}


