package com.shaiful.mynote.di.modules

import com.shaiful.mynote.data.daos.HabitCheckedDateDao
import com.shaiful.mynote.data.daos.HabitDao
import com.shaiful.mynote.data.databases.NotesDatabase
import com.shaiful.mynote.data.repository.HabitTrackerRepositoryImpl
import com.shaiful.mynote.domain.repositories.HabitTrackerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HabitRepositoryModule {

    @Provides
    @Singleton
    fun provideHabitDao(database: NotesDatabase) = database.habitDao()

    @Provides
    @Singleton
    fun provideHabitCheckedDateDao(database: NotesDatabase) = database.habitCheckedDateDao()

    @Provides
    @Singleton
    fun provideHabitRepository(habitDao: HabitDao, habitCheckedDateDao: HabitCheckedDateDao): HabitTrackerRepository {
        return HabitTrackerRepositoryImpl(habitDao, habitCheckedDateDao)
    }


}