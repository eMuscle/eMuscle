package com.example.emuscle.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_table")
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val day: String,
    val exercise: String,
    val sets: String,
    val reps: String,
    val weight: String
)