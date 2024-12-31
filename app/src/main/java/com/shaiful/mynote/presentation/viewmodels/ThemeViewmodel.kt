package com.shaiful.mynote.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ThemeViewmodel(context: Context, private val isSystemInDarkTheme: Boolean) : ViewModel() {

    private val sharedPref = context.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)

    private val _isDarkTheme = MutableStateFlow<Boolean>(isSystemInDarkTheme)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme

    init {
        checkAPppThemeAndUpdateState();
    }

    private fun checkAPppThemeAndUpdateState() {
        _isDarkTheme.value = isAppInDarkTheme()
    }

    fun isAppInDarkTheme(): Boolean {
        val appTheme = sharedPref.getString("theme", null)
        return when (appTheme) {
            null -> {
                isSystemInDarkTheme;
            }

            "dark" -> {
                true
            }

            "light" -> {
                false
            }

            else -> {
                isSystemInDarkTheme
            }
        }
    }

    fun setAppTheme(theme: String) {
        sharedPref.edit().putString("theme", theme).apply()
        checkAPppThemeAndUpdateState()
    }

}