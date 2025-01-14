package com.shaiful.mynote.data.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class Habit (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val habitName: String,
    val description: String
)