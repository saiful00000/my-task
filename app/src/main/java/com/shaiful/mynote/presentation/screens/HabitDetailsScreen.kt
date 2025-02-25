package com.shaiful.mynote.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shaiful.mynote.presentation.utility_widgets.VerticalSpace
import com.shaiful.mynote.presentation.viewmodels.HabitDetailsViewModel
import com.shaiful.mynote.presentation.widgets.AppBar
import com.shaiful.mynote.presentation.widgets.DatePickerButton
import com.shaiful.mynote.presentation.widgets.habit.HabitDetailsCalendar
import java.time.LocalDate

@Composable
fun HabitDetailsScreen(
    navController: NavController,
    habitId: Int,
    viewmodel: HabitDetailsViewModel = hiltViewModel(),
) {
    val now = LocalDate.now()

    var selectedYear by remember {
        mutableStateOf(now.year)
    }

    var selectedMonth by remember {
        mutableStateOf(now.month.value)
    }

    val habit by viewmodel.habit.collectAsState()
    val checkedDates by viewmodel.habitCheckedDates.collectAsState()

    // Call necessary functions
    viewmodel.getHabit(habitId)
    viewmodel.getCheckedDatesByMonthAndYear(habitId = habitId, month = selectedMonth, year = selectedYear)

    Scaffold(
        topBar = {
            AppBar(title = "Habit Details", navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            VerticalSpace(height = 16)
            Text(
                text = "${habit?.habitName ?: "-"} details",
                style = TextStyle(
                    fontSize = 14.sp
                )
            )
            VerticalSpace(24)
            DatePickerButton(
                onMonthPicked = { year, month ->
                    selectedYear = year
                    selectedMonth = month
                }
            )
            VerticalSpace(height = 24)
            HabitDetailsCalendar(year = selectedYear, month = selectedMonth, checkedDates = checkedDates)
        }
    }
}