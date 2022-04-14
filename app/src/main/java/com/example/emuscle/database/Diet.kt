package com.example.emuscle.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diet_table")

data class Diet(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val day: String,
    val breakFast: String,
    val breakFastCal: Int,
    val lunch: String,
    val lunchCal: Int,
    val dinner: String,
    val dinnerCal: Int,
    val supper: String,
    val supperCal: Int,
    val snacks: String,
    val snacksCal: Int
)



