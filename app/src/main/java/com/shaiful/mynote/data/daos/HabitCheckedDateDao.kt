package com.shaiful.mynote.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shaiful.mynote.data.tables.HabitCheckedDates
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitCheckedDateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCheckedDate(habitCheckedDates: HabitCheckedDates)

    @Query("SELECT * FROM habit_checked_dates WHERE habitId = :habitId")
    fun getCheckedDatesForHabit(habitId: Int): Flow<List<HabitCheckedDates>>

    @Query("SELECT * FROM habit_checked_dates WHERE habitId = :habitId AND month = :month AND year = :year")
    fun getCheckedDatesByMonthAndYearAsFlow(habitId: Int, month: Int, year: Int): Flow<List<HabitCheckedDates>>

    @Query("SELECT * FROM habit_checked_dates WHERE habitId = :habitId AND month = :month AND year = :year")
    suspend fun getCheckedDatesByMonthAndYear(habitId: Int, month: Int, year: Int): List<HabitCheckedDates>

    @Delete
    suspend fun deleteCheckedDate(habitCheckedDates: HabitCheckedDates)

}