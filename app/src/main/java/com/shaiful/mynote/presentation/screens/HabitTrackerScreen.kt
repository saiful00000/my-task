package com.shaiful.mynote.presentation.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shaiful.mynote.data.tables.Habit
import com.shaiful.mynote.data.tables.HabitCheckedDates
import com.shaiful.mynote.domain.DayType
import com.shaiful.mynote.presentation.navigation.RouteNames
import com.shaiful.mynote.presentation.utility_widgets.VerticalSpace
import com.shaiful.mynote.presentation.viewmodels.HabitTrackerViewModel
import com.shaiful.mynote.presentation.widgets.AppBar
import com.shaiful.mynote.presentation.widgets.ConfirmationDialog
import com.shaiful.mynote.presentation.widgets.NilWidget
import com.shaiful.mynote.presentation.widgets.ThinButton
import com.shaiful.mynote.presentation.widgets.habit.HabitCreationDialog
import com.shaiful.mynote.presentation.widgets.habit.HabitStatusIcon
import com.shaiful.mynote.presentation.widgets.notes.HabitOptionMenuButton
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun HabitTrackerScreen(
    navController: NavController, viewmodel: HabitTrackerViewModel = hiltViewModel()
) {

    val habitList by viewmodel.allHabits.collectAsState()

    var showHabitCreationDialog by remember {
        mutableStateOf(false)
    }

    var showDeleteConfirmationDialog by remember {
        mutableStateOf(false)
    }
    var habitToDelete: Habit? = null

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
            }, onDismiss = {
                showHabitCreationDialog = false
            })
        }

        if (showDeleteConfirmationDialog) {
            ConfirmationDialog(
                title = "Delete Habit",
                message = "Are you sure you want to delete this habit?",
                onDismiss = {
                    showDeleteConfirmationDialog = false
                },
                onConfirm = {
                    showDeleteConfirmationDialog = false
                    if (habitToDelete != null) {
                        viewmodel.deleteHabit(habitToDelete!!)
                    }
                },
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
                    }, text = "Add New Habit"
                )
            }
        } else {
            LazyColumn(modifier = Modifier.padding(it)) {
                itemsIndexed(habitList) { index, habit ->
                    Column(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp),
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = habit.habitName,
                                    style = TextStyle(fontWeight = FontWeight(700)),
                                    modifier = Modifier.weight(1F)
                                )
                                HabitOptionMenuButton(
                                    onDetails = {
                                        navController.navigate(RouteNames.habitDetailsScreen + "/${habit.id}")
                                    },
                                    onDelete = {
                                        showDeleteConfirmationDialog = true
                                        habitToDelete = habit
                                    },
                                )
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Calculate dates
                            val currentDate = LocalDate.now()
                            val currentMonth = currentDate.month.value
                            val currentYear = currentDate.year
                            val dateFormatter = DateTimeFormatter.ofPattern("dd MMM")

                            val databaseDateFormater = DateTimeFormatter.ofPattern("yyyy-MM-dd")

//                            val dates = List(5) { offset ->
//                                currentDate.plusDays((offset - 2).toLong()) // Generate -2, -1, 0, +1, +2 days
//                            }

                            var thisWeekCheckedDatesCount = 0
                            val dates = viewmodel.getDatesOfCurrentWeek()

                            val checkedDates by viewmodel.getCheckedDatesByMonthAndYearAsFlow(
                                habit.id,
                                currentMonth,
                                currentYear
                            ).collectAsState()

                            dates.forEachIndexed { index, date ->
                                val dayType =
                                    if (date == currentDate) DayType.Current else if (currentDate.isAfter(
                                            date
                                        )
                                    ) DayType.Previous else DayType.Forward

                                var isChecked = false
                                var currentHabitCheckedDate: HabitCheckedDates? = null

                                checkedDates.forEach {
                                    if (date.format(databaseDateFormater) == it.date) {
                                        isChecked = true
                                        thisWeekCheckedDatesCount++
                                        currentHabitCheckedDate = it
                                    }
                                }

                                Card(
                                    elevation = CardDefaults.cardElevation(1.dp),
                                    shape = RoundedCornerShape(5.dp),
                                    border = if (dayType == DayType.Current) BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary) else null,
                                    modifier = Modifier
                                        .weight(1F)
                                        .padding(4.dp),
                                    onClick = {
                                        if (dayType == DayType.Current || dayType == DayType.Previous) {
                                            when (isChecked) {
                                                true -> {
                                                    if (currentHabitCheckedDate != null) {
                                                        viewmodel.deleteCheckedDate(
                                                            currentHabitCheckedDate!!
                                                        )
                                                    }
                                                }

                                                false -> {
                                                    viewmodel.insertCheckedDate(
                                                        HabitCheckedDates(
                                                            habitId = habit.id,
                                                            date = date.format(databaseDateFormater),
                                                            month = date.month.value,
                                                            year = date.year,
                                                        )
                                                    )
                                                }
                                            }
                                        }
                                    },
                                    ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(
                                                horizontal = 4.dp, vertical = 10.dp
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.SpaceEvenly,
                                        ) {
                                            HabitStatusIcon(
                                                dayType = dayType,
                                                isChecked = isChecked
                                            )
                                            VerticalSpace(height = 4)
                                            Text(
                                                text = date.dayOfWeek.getDisplayName(
                                                    java.time.format.TextStyle.SHORT,
                                                    Locale.getDefault()
                                                ),
                                                style = TextStyle(
                                                    fontWeight = if (dayType == DayType.Current) FontWeight.Bold else FontWeight.Normal,
                                                    fontSize = 9.sp,
                                                )
                                            )
                                            Text(
                                                text = date.format(dateFormatter),
                                                style = TextStyle(
                                                    fontWeight = if (dayType == DayType.Current) FontWeight.Bold else FontWeight.Normal,
                                                    fontSize = 8.sp,
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
                                }, text = "Add New Habit"
                            )
                            VerticalSpace(height = 56)
                        }
                    }
                }
            }
        }
    }
}

