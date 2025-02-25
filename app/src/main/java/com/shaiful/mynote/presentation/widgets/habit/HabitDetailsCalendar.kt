package com.shaiful.mynote.presentation.widgets.habit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shaiful.mynote.data.tables.HabitCheckedDates
import com.shaiful.mynote.presentation.models.HabitCheckedDateWrapper
import java.time.LocalDate

@Composable
fun HabitDetailsCalendar(year: Int, month: Int, checkedDates: List<HabitCheckedDates>) {
    val weekDaysStrs = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    val firstDayOfMonth = LocalDate.of(year, month + 1, 1)
    val daysInMonth = firstDayOfMonth.lengthOfMonth()
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value // 1 = Monday --- 7 = Sunday

    val checkedDays = checkedDates.map { (it.date.substring(8, 10).toInt()) }.toList()

    val daysList = mutableListOf<HabitCheckedDateWrapper>()
    // Fill empty spaces for days before the 1st day of month
    repeat((firstDayOfWeek - 1) % 7) {
        daysList.add(HabitCheckedDateWrapper(null, false))
    }
    // Add actual days of the month
    for (day in 1..daysInMonth) {
        daysList.add(
            HabitCheckedDateWrapper(LocalDate.of(year, month + 1, day), checkedDays.contains(day))
        )
    }
    // Fill remaining empty spaces to complete the last row
    while (daysList.size % 7 != 0) {
        daysList.add(HabitCheckedDateWrapper(null, false))
    }


    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            weekDaysStrs.forEach {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.weight(1F),
                ) {
                    Text(
                        text = it,
                        textAlign = TextAlign.Center,
//                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                    )
                }
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
        ) {
            items(daysList) { data ->
                Box (
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(1.dp)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(2.dp))
                        .background(if (data.isChecked) Color.Green.copy(alpha = 0.3f) else Color.Transparent)
                        .padding(vertical = 10.dp)
                ) {
                    data.localDate?.let {
                        Text(
                            text = it.dayOfMonth.toString(),
                            style = TextStyle(
                                fontWeight = FontWeight.Light
                            )
                        )
                    }
                }
            }
        }
    }
}