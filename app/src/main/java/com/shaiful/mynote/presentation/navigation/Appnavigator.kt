package com.shaiful.mynote.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shaiful.mynote.presentation.screens.HabitDetailsScreen
import com.shaiful.mynote.presentation.screens.HabitTrackerScreen
import com.shaiful.mynote.presentation.screens.HomeScreen
import com.shaiful.mynote.presentation.screens.StopwatchScreen

@Composable
fun AppNavigator(onThemeChange: (Boolean) -> Unit, isDarkTheme: Boolean) {
    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = RouteNames.startingDestination) {
        composable(RouteNames.homeScreen) {
            HomeScreen(navController = navHostController, onThemeChange = onThemeChange, isDarkTheme = isDarkTheme)
        }
        composable(RouteNames.stopwatchScreen) {
            StopwatchScreen(navController = navHostController)
        }
        composable (RouteNames.habitTrackerScreen) {
            HabitTrackerScreen(navController = navHostController)
        }
        composable (RouteNames.habitDetailsScreen + "/{habitId}") {
            val habitId = it.arguments?.getString("habitId")?.toIntOrNull()
            HabitDetailsScreen(navController = navHostController, habitId ?: 0)
        }
    }
}