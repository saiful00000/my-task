package com.shaiful.mynote.data.databases

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.shaiful.mynote.data.daos.CategoryDao
import com.shaiful.mynote.data.daos.HabitCheckedDateDao
import com.shaiful.mynote.data.daos.HabitDao
import com.shaiful.mynote.data.daos.NoteDao
import com.shaiful.mynote.data.tables.Category
import com.shaiful.mynote.data.tables.Habit
import com.shaiful.mynote.data.tables.HabitCheckedDates
import com.shaiful.mynote.data.tables.Note


@Database(
    entities = [Category::class, Note::class, Habit::class, HabitCheckedDates::class],
    version = 2,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
//        AutoMigration(from = 2, to = 3)
    ]
)
abstract class NotesDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun categoryDao(): CategoryDao
    abstract fun habitDao(): HabitDao
    abstract fun habitCheckedDateDao(): HabitCheckedDateDao
}