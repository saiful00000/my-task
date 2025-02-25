package com.shaiful.mynote.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaiful.mynote.data.tables.Habit
import com.shaiful.mynote.data.tables.HabitCheckedDates
import com.shaiful.mynote.domain.repositories.HabitTrackerRepository
import com.shaiful.mynote.utils.DateUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitDetailsViewModel @Inject constructor(
    private val repository: HabitTrackerRepository,
    private val dateUtils: DateUtils
) : ViewModel() {

    private val _habit = MutableStateFlow<Habit?>(null)
    val habit: StateFlow<Habit?> = _habit

    fun getHabit(id: Int) {
        viewModelScope.launch {
             _habit.value = repository.getHabitById(id)
        }
    }

    private val _habitCheckedDates = MutableStateFlow<List<HabitCheckedDates>>(mutableListOf())
    val habitCheckedDates: StateFlow<List<HabitCheckedDates>> = _habitCheckedDates

    fun getCheckedDatesByMonthAndYear(habitId: Int, month: Int, year: Int) {
        viewModelScope.launch {
            _habitCheckedDates.value = repository.getCheckedDatesByMonthAndYear(year = year, month = month, habitId = habitId)
        }
    }

}