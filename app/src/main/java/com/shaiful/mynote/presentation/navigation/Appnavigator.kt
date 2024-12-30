package com.shaiful.mynote.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shaiful.mynote.presentation.screens.HomeScreen

@Composable
fun AppNavigator() {
    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = RouteNames.homeScreen) {
        composable(RouteNames.homeScreen) {
            HomeScreen(navController = navHostController)
        }
    }
}