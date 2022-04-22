package com.example.emuscle.database

import androidx.room.Entity
import androidx.room.PrimaryKey

//Muodostetaan User taulu tietokantaan
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val height: String,
    val weight: String,
    val bodyFat: String
)