package com.shaiful.mynote.domain.repositories

import com.shaiful.mynote.data.tables.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun insert(note: Note)
    suspend fun delete(note: Note)
    fun  getNotesByCategory(categoryId: Int): Flow<List<Note>>
    suspend fun update(note: Note)
}