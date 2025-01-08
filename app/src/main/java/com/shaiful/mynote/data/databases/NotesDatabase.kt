package com.shaiful.mynote.data.databases

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.shaiful.mynote.data.daos.CategoryDao
import com.shaiful.mynote.data.daos.NoteDao
import com.shaiful.mynote.data.tables.Category
import com.shaiful.mynote.data.tables.Note


@Database(
    entities = [Category::class, Note::class],
    version = 1,
    exportSchema = true,
    autoMigrations = [
//        AutoMigration(from = 1, to = 2),
//        AutoMigration(from = 2, to = 3)
    ]
)
abstract class NotesDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun categoryDao(): CategoryDao
}