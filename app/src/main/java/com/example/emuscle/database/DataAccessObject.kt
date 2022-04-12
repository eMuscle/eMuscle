package com.example.emuscle.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DataAccessObject {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addExercise(exercise: Exercise)

    @Update
    suspend fun updateExercise(exercise: Exercise)

    @Query("DELETE FROM exercise_table WHERE id = :id")
    suspend fun deleteExercise(id: String)

    @Query("SELECT * FROM exercise_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Exercise>>

    @Query("SELECT * FROM exercise_table WHERE day = :day")
    fun getExercisesByDay(day: String): LiveData<List<Exercise>>
}