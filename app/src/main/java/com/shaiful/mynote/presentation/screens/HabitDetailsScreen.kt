package com.shaiful.mynote.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.shaiful.mynote.presentation.viewmodels.HabitTrackerViewModel
import com.shaiful.mynote.presentation.widgets.AppBar

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
            modifier = Modifier.padding(innerPadding)
        ) {
            Text(habitId.toString())
        }
    }
}