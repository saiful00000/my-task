package com.shaiful.mynote.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.shaiful.mynote.presentation.widgets.habit.HabitDetailsCalender
import java.time.LocalDate

@Composable
fun HabitDetailsScreen(
    navController: NavController,
    habitId: Int,
    viewmodel: HabitDetailsViewModel = hiltViewModel(),
) {
    val now = LocalDate.now()

    val habit by viewmodel.habit.collectAsState()

    // Call necessary functions
    viewmodel.getHabit(habitId)
    val checkedDates by viewmodel.getCheckedDatesByMonthAndYear(
        habitId = habitId,
        month = now.month.value,
        year = now.year
    ).collectAsState()

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

            Text(
                text = "${habit?.habitName ?: "-"} details ythejkdf dshfds sjnb shdudf adssfahd",
                style = TextStyle(
                    fontSize = 14.sp
                )
            )
            VerticalSpace(16)
            DatePickerButton(
                onMonthPicked = { year, month ->

                }
            )
            VerticalSpace(height = 16)
            HabitDetailsCalender(year = 2025, month = 2, checkedDates = checkedDates)
        }
    }
}