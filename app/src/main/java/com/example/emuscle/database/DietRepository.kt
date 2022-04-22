package com.example.emuscle.database

import androidx.lifecycle.LiveData


//Väliluokka, joka ohjaa DietViewModelissa olevat funktiot tietokantaan
class DietRepository(private val dataAccessObject: DataAccessObject){

    fun getDietByDay(day: String): LiveData<List<Diet>> {
        return dataAccessObject.getDietByDay(day)
    }

    suspend fun addDiet(diet: Diet){
        dataAccessObject.addDiet(diet)
    }

    suspend fun updateDiet(diet: Diet){
        dataAccessObject.updateDiet(diet)
    }

    suspend fun deleteDiet(id: String){
        dataAccessObject.deleteDiet(id)
    }
}