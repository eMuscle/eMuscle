package com.example.emuscle.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DietViewModel(application: Application): AndroidViewModel(application) {

    private val repository: DietRepository


    init {
        val dataAccessObject = ExerciseDB.getDB(application).exerciseDao()
        repository = DietRepository(dataAccessObject)
    }

    fun getDietByDay(day: String): Diet {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getDietByDay(day)
        }
    }

    fun addDiet(diet: Diet){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDiet(diet)
        }
    }

    fun updateDiet(diet: Diet){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDiet(diet)
        }
    }

    fun deleteDiet(id: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteDiet(id)
        }
    }
}