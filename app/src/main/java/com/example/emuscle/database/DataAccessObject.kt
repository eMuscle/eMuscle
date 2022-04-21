package com.example.emuscle.database

import androidx.lifecycle.LiveData
import androidx.room.*

//Määritetään tarvittavat SQL komennot
@Dao
interface DataAccessObject {

    //Exercise osio

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

    //Diet osio

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDiet(diet: Diet)

    @Update
    suspend fun updateDiet(diet: Diet)

    @Query("DELETE FROM diet_table WHERE id = :id")
    suspend fun deleteDiet(id: String)

    @Query("SELECT * FROM diet_table WHERE day = :day")
    fun getDietByDay(day: String): LiveData<List<Diet>>

    //User osio

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table WHERE id = (SELECT max(id) FROM user_table)")
    fun getLastRecordUser(): User

}