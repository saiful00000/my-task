package com.shaiful.mynote.data.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shaiful.mynote.data.daos.CategoryDao
import com.shaiful.mynote.data.daos.NoteDao
import com.shaiful.mynote.data.tables.Category
import com.shaiful.mynote.data.tables.Note


@Database(
    entities = [Category::class, Note::class],
    version = 1,
    exportSchema = false
)
abstract class NotesDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun categoryDao(): CategoryDao
}