package com.example.emuscle.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataAccessObject {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addExercise(exercise: Exercise)

    @Query("SELECT * FROM exercise_table WHERE day = :day")
    fun readAllData(day: String): LiveData<List<Exercise>>
}