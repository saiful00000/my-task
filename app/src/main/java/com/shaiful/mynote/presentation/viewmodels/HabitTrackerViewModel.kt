package com.shaiful.mynote.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.shaiful.mynote.data.daos.HabitCheckedDateDao
import com.shaiful.mynote.data.daos.HabitDao
import com.shaiful.mynote.data.databases.NotesDatabase
import com.shaiful.mynote.data.repository.HabitRepositoryImpl
import dagger.Provides
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class HabitTrackerViewModel @Inject constructor() : ViewModel() {

    @Provides
    @Singleton
    fun provideHabitDao(database: NotesDatabase) = database.habitDao()

    @Provides
    @Singleton
    fun provideHabitCheckedDateDao(database: NotesDatabase) = database.habitCheckedDateDao()

    @Provides
    @Singleton
    fun provideHabitRepository(habitDao: HabitDao, habitCheckedDateDao: HabitCheckedDateDao): HabitRepositoryImpl {
        return HabitRepositoryImpl(habitDao, habitCheckedDateDao)
    }
}