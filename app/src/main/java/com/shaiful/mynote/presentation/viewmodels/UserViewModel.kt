package com.shaiful.mynote.presentation.viewmodels

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserViewModel(context: Context) : ViewModel() {
    private val sharedPref =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    private val _userName = MutableStateFlow<String?>(null)
    val userName: StateFlow<String?> = _userName

    init {
        _userName.value = sharedPref.getString("username", null)
    }

    fun setUserName(name: String) {
        _userName.value = name
        sharedPref.edit().putString("username", name).apply()
    }

}