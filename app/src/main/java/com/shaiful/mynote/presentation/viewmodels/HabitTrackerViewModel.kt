package com.shaiful.mynote.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaiful.mynote.data.tables.Habit
import com.shaiful.mynote.data.tables.HabitCheckedDates
import com.shaiful.mynote.domain.repositories.HabitTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitTrackerViewModel @Inject constructor(
    private val repository: HabitTrackerRepository
) : ViewModel() {

    val allHabits = repository.getHabits().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList(),
    )

    fun addHabit(habit: Habit) = viewModelScope.launch {
        repository.insertHabit(habit)
    }

    fun updateHabit(habit: Habit) = viewModelScope.launch {
        repository.updateHabit(habit)
    }

    fun deleteHabit(habit: Habit) = viewModelScope.launch {
        repository.deleteHabit(habit)
    }

    private val checkedDatesCache = mutableMapOf<Int, StateFlow<List<HabitCheckedDates>>>()

    fun getCheckedDatesForHabit(habitId: Int): StateFlow<List<HabitCheckedDates>> {
        return checkedDatesCache.getOrPut(habitId) {
            repository.getCheckedDatesForHabit(habitId).stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )
        }
    }

    fun insertCheckedDate(habitCheckedDates: HabitCheckedDates) = viewModelScope.launch {
        repository.insertCheckedDate(habitCheckedDates)
    }
}