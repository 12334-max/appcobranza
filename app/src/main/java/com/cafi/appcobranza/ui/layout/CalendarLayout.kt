package com.cafi.appcobranza.ui.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cafi.appcobranza.model.WalletSelected
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

class CalendarLayout {
    @Composable
    fun CalendarScreen(
        events: List<WalletSelected>,
        onDataSelected: (LocalDate) -> Unit
    ){
        val currentMonth = remember { YearMonth.now() }
        val startMonth = remember { currentMonth.minusMonths(100) }
        val endMonth = remember { currentMonth.plusMonths(100) }
        val daysOfWeek = remember { daysOfWeek() }

        val state = rememberCalendarState(
            startMonth = startMonth,
            endMonth = endMonth,
            firstVisibleMonth = currentMonth,
            firstDayOfWeek = daysOfWeek.first()
        )
        val selectedDateState = remember { mutableStateOf<LocalDate?>(LocalDate.now()) }

        HorizontalCalendar(
            state = state,
            dayContent = { day ->
                Day(
                    day = day,
                    isSelected = selectedDateState.value == day.date,
                    events = events,
                    onClick = { selectedDate ->
                        selectedDateState.value = selectedDate
                        onDataSelected(selectedDate)
                    }
                )
            },
            monthHeader = {
                month -> Column() {
                MonthHeader(yearMonth = month.yearMonth)
                DaysOfWeekTitle(daysOfWeek = daysOfWeek)

            } },
            calendarScrollPaged = true,
            userScrollEnabled = true
        )
    }

    @Composable
    fun DaysOfWeekTitle(daysOfWeek: List<DayOfWeek>) {
        Row(modifier = Modifier.fillMaxWidth()) {
            for (dayOfWeek in daysOfWeek) {
                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                )
            }
        }
    }

    @Composable
    fun MonthHeader(yearMonth: YearMonth) {
        val monthName = yearMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
        val year = yearMonth.year
        Text(
            text = "${monthName.uppercase()} $year",
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 10.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6
        )
    }


    @Composable
    fun Day(
        day: CalendarDay,
        isSelected: Boolean,
        events: List<WalletSelected>,
        onClick: (LocalDate) -> Unit
    ) {
        val hasEvent = events.any { it.fechaVisita == day.date }
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .clip(CircleShape)
                .background(color = if (isSelected) Color.Cyan else Color.Transparent)
                .clickable(
                    enabled = day.position == DayPosition.MonthDate
                ) {
                    onClick(day.date)
                },
            contentAlignment = Alignment.Center
        ) {
            Column {
                Text(
                    text = day.date.dayOfMonth.toString(),
                    color = if (day.position == DayPosition.MonthDate) Color.Black else Color.Gray
                )
                if (hasEvent) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .background(Color.Green)
                            .clip(CircleShape)
                    )
                }
            }
        }
    }
}

