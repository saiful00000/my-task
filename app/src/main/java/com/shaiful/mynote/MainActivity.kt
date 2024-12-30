package com.shaiful.mynote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.shaiful.mynote.presentation.navigation.AppNavigator
import com.shaiful.mynote.ui.theme.MyNoteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val isSystemDarkTheme = isSystemInDarkTheme()
            var isDarkTheme by remember { mutableStateOf(isSystemDarkTheme) }

            MyNoteTheme (
                darkTheme = isDarkTheme,
            ) {
                AppNavigator(
                    onThemeChange = {
                        isDarkTheme = it
                    }
                )
            }
        }
    }
}