package com.shaiful.mynote.di.modules

import android.content.Context
import androidx.room.Room
import com.shaiful.mynote.data.daos.CategoryDao
import com.shaiful.mynote.data.daos.NoteDao
import com.shaiful.mynote.data.databases.NotesDatabase
import com.shaiful.mynote.data.repository.CategoryRepositoryImpl
import com.shaiful.mynote.data.repository.NoteRepositoryImp
import com.shaiful.mynote.domain.repositories.CategoryRepository
import com.shaiful.mynote.domain.repositories.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NoteRepositoryModule {
    @Provides
    @Singleton
    fun provideNoteDao(database: NotesDatabase) = database.noteDao()

    @Provides
    @Singleton
    fun provideCategoryDao(database: NotesDatabase) = database.categoryDao()

    @Provides
    @Singleton
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository {
        return NoteRepositoryImp(noteDao)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(categoryDao: CategoryDao): CategoryRepository {
        return  CategoryRepositoryImpl(categoryDao)
    }
}