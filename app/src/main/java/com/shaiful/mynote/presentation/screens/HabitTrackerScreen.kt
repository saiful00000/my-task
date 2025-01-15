package com.shaiful.mynote.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shaiful.mynote.data.tables.Habit
import com.shaiful.mynote.presentation.utility_widgets.VerticalSpace
import com.shaiful.mynote.presentation.viewmodels.HabitTrackerViewModel
import com.shaiful.mynote.presentation.widgets.AppBar
import com.shaiful.mynote.presentation.widgets.NilWidget
import com.shaiful.mynote.presentation.widgets.ThinButton
import com.shaiful.mynote.presentation.widgets.habit.HabitCreationDialog
import com.shaiful.mynote.ui.theme.PriorityMedium
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun HabitTrackerScreen(
    navController: NavController,
    viewmodel: HabitTrackerViewModel = hiltViewModel()
) {

    val habitList by viewmodel.allHabits.collectAsState()

    var showHabitCreationDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            AppBar(title = "Habit Tracker", navController = navController)
        },
    ) {

        if (showHabitCreationDialog) {
            HabitCreationDialog(onSave = {
                viewmodel.addHabit(
                    Habit(habitName = it, description = "")
                )
                showHabitCreationDialog = false
            },
                onDismiss = {
                    showHabitCreationDialog = false
                }
            )
        }

        if (habitList.isEmpty()) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                NilWidget(message = "No Habit Yet!")
                VerticalSpace(height = 24)
                ThinButton(
                    onClick = {
                        showHabitCreationDialog = true
                    },
                    text = "Add New Habit"
                )
            }
        } else {
            LazyColumn(modifier = Modifier.padding(it)) {
                itemsIndexed(habitList) { index, habit ->
                    Column(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(start = 8.dp, bottom = 16.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(5.dp))
                                .padding(top = 8.dp, bottom = 8.dp, start = 8.dp),
                        ) {
                            Text(
                                text = habit.habitName,
                                style = TextStyle(fontWeight = FontWeight(700)),
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Calculate dates
                            val currentDate = LocalDate.now()
                            val dateFormatter = DateTimeFormatter.ofPattern("dd MMM")

                            val databaseDateFormater = DateTimeFormatter.ofPattern("yyyy-MM-dd")

                            val dates = List(5) { offset ->
                                currentDate.plusDays((offset - 2).toLong()) // Generate -2, -1, 0, +1, +2 days
                            }

                            dates.forEachIndexed { index, date ->
                                val isCurrentDate = date == currentDate
                                val isPreviousDate = currentDate.isAfter(date)
                                val isForwardDate = currentDate.isBefore(date)

                                val checkedDates by viewmodel.getCheckedDatesForHabit(habit.id).collectAsState()

                                Card(
                                    elevation = CardDefaults.cardElevation(1.dp),
                                    onClick = {},
                                    shape = RoundedCornerShape(4.dp)
                                ) {
                                    Box(
                                        modifier = Modifier.padding(
                                            horizontal = 4.dp,
                                            vertical = 10.dp
                                        ),
                                    ) {
                                        Column (
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center,

                                        ) {
                                            Text(
                                                text = date.dayOfWeek.getDisplayName(
                                                    java.time.format.TextStyle.SHORT,
                                                    Locale.getDefault()
                                                ),
                                                style = TextStyle(
                                                    fontWeight = if (isCurrentDate) FontWeight.Bold else FontWeight.Normal,
                                                    fontSize = 12.sp,
                                                )
                                            )



                                            Text(
                                                text = date.format(dateFormatter),
                                                style = TextStyle(
                                                    fontWeight = if (isCurrentDate) FontWeight.Bold else FontWeight.Normal,
                                                    fontSize = 10.sp,
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        if (index == habitList.size - 1) {
                            VerticalSpace(height = 24)
                            ThinButton(
                                onClick = {
                                    showHabitCreationDialog = true
                                },
                                text = "Add New Habit"
                            )
                            VerticalSpace(height = 56)
                        }
                    }
                }
            }
        }
    }
}