package com.shaiful.mynote.presentation.viewmodels

import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StopwatchViewModel @Inject constructor() : ViewModel() {

    private val _time = MutableStateFlow(0L)
    val time: StateFlow<Long> = _time

    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning

    private var startTime = 0L
    private var timerJob: Job? = null

    fun start() {
        if (_isRunning.value) return
        _isRunning.value = true
        startTime = System.currentTimeMillis() - _time.value
        timerJob = viewModelScope.launch {
            while (_isRunning.value) {
                _time.value = System.currentTimeMillis() - startTime
                delay(10L)
            }
        }
    }

    fun pause() {
        _isRunning.value = false
        timerJob?.cancel()
    }

    fun resume() {
        start()
    }

    fun restart() {
        pause()
        _time.value = 0L
    }

    fun formatTime(timeInMillis: Long): String {
        val totalSeconds = timeInMillis / 1000
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60
        val milliseconds = timeInMillis % 1000

        return String.format("%02d:%02d:%02d:%03d", hours, minutes, seconds, milliseconds)
    }
}