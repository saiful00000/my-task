package com.shaiful.mynote.di.modules

import com.shaiful.mynote.data.daos.HabitCheckedDateDao
import com.shaiful.mynote.data.daos.HabitDao
import com.shaiful.mynote.data.databases.NotesDatabase
import com.shaiful.mynote.data.repository.HabitRepositoryImpl
import com.shaiful.mynote.domain.repositories.HabitRepository
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
    fun provideHabitRepository(habitDao: HabitDao, habitCheckedDateDao: HabitCheckedDateDao): HabitRepository {
        return HabitRepositoryImpl(habitDao, habitCheckedDateDao)
    }


}