package com.example.emuscle.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExerciseViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Exercise>>
    private val repository: ExerciseRepository

    init {
        val dataAccessObject = EmuscleDB.getDB(application).exerciseDao()
        repository = ExerciseRepository(dataAccessObject)
        readAllData = repository.readAllData
    }

    fun getExercisesByDay(day: String): LiveData<List<Exercise>> {
        return repository.getExercisesByDay(day)
    }

    fun addExercise(exercise: Exercise) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addExercise(exercise)
        }
    }

    fun updateExercise(exercise: Exercise) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateExercise(exercise)
        }
    }

    fun deleteExercise(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteExercise(id)
        }
    }
}