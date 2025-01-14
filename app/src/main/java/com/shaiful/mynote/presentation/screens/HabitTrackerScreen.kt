package com.shaiful.mynote.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shaiful.mynote.presentation.viewmodels.HabitTrackerViewModel
import com.shaiful.mynote.presentation.widgets.AppBar

@Composable
fun HabitTrackerScreen(
    navController: NavController,
    viewmodel: HabitTrackerViewModel = hiltViewModel()
) {
    Scaffold (
        topBar = {
            AppBar(title = "Habit Tracker", navController = navController)
        },
        content = {
            Column(modifier = Modifier.padding(it)) {

            }
        }
    )
}