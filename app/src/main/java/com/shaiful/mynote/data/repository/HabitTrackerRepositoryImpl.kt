package com.shaiful.mynote.data.repository

import com.shaiful.mynote.data.daos.HabitCheckedDateDao
import com.shaiful.mynote.data.daos.HabitDao
import com.shaiful.mynote.data.tables.Habit
import com.shaiful.mynote.data.tables.HabitCheckedDates
import com.shaiful.mynote.domain.repositories.HabitTrackerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HabitTrackerRepositoryImpl @Inject constructor(
    private val habitDao: HabitDao,
    private val habitCheckedDateDao: HabitCheckedDateDao
) : HabitTrackerRepository {
    override suspend fun insertHabit(habit: Habit) = habitDao.insertHabit(habit)

    override suspend fun updateHabit(habit: Habit) = habitDao.updateHabit(habit)

    override suspend fun deleteHabit(habit: Habit) = habitDao.deleteHabit(habit)

    override fun getHabits(): Flow<List<Habit>> = habitDao.getAllHabits()

    override suspend fun insertCheckedDate(habitCheckedDates: HabitCheckedDates) = habitCheckedDateDao.insertCheckedDate(habitCheckedDates)

    override fun getCheckedDatesForHabit(habitId: Int): Flow<List<HabitCheckedDates>> = habitCheckedDateDao.getCheckedDatesForHabit(habitId)

}