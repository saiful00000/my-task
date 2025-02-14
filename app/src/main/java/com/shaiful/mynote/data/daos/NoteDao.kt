package com.shaiful.mynote.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.shaiful.mynote.data.tables.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM notes WHERE categoryId = :categoryId ORDER BY priority DESC")
    fun getNotesByCategory(categoryId: Int): Flow<List<Note>>

    @Update
    suspend fun update(note: Note)

    @Query("DELETE FROM notes WHERE categoryId = :categoryId")
    suspend fun deleteNotesByCategory(categoryId: Int)

    @Query("DELETE FROM notes WHERE categoryId = :categoryId AND isDone = 1")
    suspend fun deleteCompletedNotesByCategory(categoryId: Int)
}