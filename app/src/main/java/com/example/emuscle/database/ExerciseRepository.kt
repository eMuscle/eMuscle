package com.example.emuscle.database

import androidx.lifecycle.LiveData

class ExerciseRepository(private val dataAccessObject: DataAccessObject) {

    val readAllData: LiveData<List<Exercise>> = dataAccessObject.readAllData()

    fun getExercisesByDay(day: String): LiveData<List<Exercise>> {
        return dataAccessObject.getExercisesByDay(day)
    }

    suspend fun addExercise(exercise: Exercise){
        dataAccessObject.addExercise(exercise)
    }

    suspend fun updateExercise(exercise: Exercise) {
        dataAccessObject.updateExercise(exercise)
    }

    suspend fun deleteExercise(id: String) {
        dataAccessObject.deleteExercise(id)
    }
}