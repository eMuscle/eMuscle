package com.example.emuscle.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diet_table")

data class Diet(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val day: String,
    val time: String,
    val food: String,
    val calories: String
)



