package com.shaiful.mynote.presentation.widgets

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
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
        AnimatedContent(
            targetState = isDarkTheme.value,
            transitionSpec = {
                if (targetState) {
                    slideInHorizontally { width -> width } + fadeIn() togetherWith
                            slideOutHorizontally {width -> -width } + fadeOut()
                } else {
                    slideInHorizontally { width -> -width } + fadeIn() togetherWith
                            slideOutHorizontally { width -> width } + fadeOut()
                }.using(SizeTransform(clip = false))
            }, label = "theme_icon_animation"
        ) { targetIsDark ->
            Icon(
                imageVector = if (targetIsDark) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                contentDescription = if (targetIsDark) "Dark Mode" else "Light Mode",
                tint = if (targetIsDark) Color.White else Color.Black
            )
        }
    }
}