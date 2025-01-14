package com.shaiful.mynote.di.modules

import android.content.Context
import androidx.room.Room
import com.shaiful.mynote.data.databases.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NotesDatabase {
        return Room
            .databaseBuilder(context, NotesDatabase::class.java, "note_database")
            .addMigrations()
            .build()
    }
}