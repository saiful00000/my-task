package com.shaiful.mynote.presentation.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

@Composable
fun ThemeToggleButton(initialThemeIsDark: Boolean, onThemeChange: (Boolean) -> Unit) {
    val isDarkTheme = remember { mutableStateOf(initialThemeIsDark) }
    IconButton(onClick = {
        isDarkTheme.value = !isDarkTheme.value
        onThemeChange(isDarkTheme.value)
    }) {
        Icon(
            imageVector = if (isDarkTheme.value) Icons.Filled.LightMode else Icons.Filled.DarkMode,
            contentDescription = if (isDarkTheme.value) "Dark Mode" else "Light Mode",
            tint = if (isDarkTheme.value) Color.White else Color.Black
        )
    }
}
