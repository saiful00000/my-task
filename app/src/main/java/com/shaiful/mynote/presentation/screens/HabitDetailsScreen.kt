package com.shaiful.mynote.presentation.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.shaiful.mynote.presentation.viewmodels.HabitTrackerViewModel
import com.shaiful.mynote.presentation.widgets.AppBar
import com.shaiful.mynote.presentation.widgets.DatePickerButton

@Composable
fun HabitDetailsScreen(
    navController: NavController,
    habitId: Int,
) {

    Scaffold (
        topBar = {
            AppBar(title = "Habit Details", navController = navController)
        }
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            DatePickerButton(
                onMonthPicked = {year, month ->

                }
            )
        }
    }
}