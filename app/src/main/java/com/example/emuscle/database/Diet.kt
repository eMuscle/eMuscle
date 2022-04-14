package com.example.emuscle.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diet_table")

data class Diet(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val day: String,
    val breakFast: String,
    val breakFastCal: String,
    val lunch: String,
    val lunchCal: String,
    val dinner: String,
    val dinnerCal: String,
    val supper: String,
    val supperCal: String,
    val snacks: String,
    val snacksCal: String
)



