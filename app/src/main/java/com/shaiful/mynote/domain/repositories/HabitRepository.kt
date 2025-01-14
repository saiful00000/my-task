package com.shaiful.mynote.domain.repositories

import com.shaiful.mynote.data.tables.Habit
import com.shaiful.mynote.data.tables.HabitCheckedDates
import kotlinx.coroutines.flow.Flow

interface HabitRepository {
    suspend fun insertHabit(habit: Habit)
    suspend fun updateHabit(habit: Habit)
    suspend fun deleteHabit(habit: Habit)
    fun getHabits(): Flow<List<Habit>>

    suspend fun insertCheckedDate(habitCheckedDates: HabitCheckedDates)
    fun getCheckedDatesForHabit(habitId: Int): Flow<List<HabitCheckedDates>>

}