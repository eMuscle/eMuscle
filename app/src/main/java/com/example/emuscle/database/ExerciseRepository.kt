package com.example.emuscle.database

import androidx.lifecycle.LiveData

class ExerciseRepository(private val dataAccessObject: DataAccessObject) {

    val readAllData: LiveData<List<Exercise>> = dataAccessObject.readAllData(day = "20/4/2022")

    suspend fun addExercise(exercise: Exercise){
        dataAccessObject.addExercise(exercise)
    }
}