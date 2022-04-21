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
        val dataAccessObject = EmuscleDB.getInstance(application).exerciseDao()
        repository = DietRepository(dataAccessObject)
    }

    fun getDietByDay(day: String): LiveData<List<Diet>> {
        return repository.getDietByDay(day)
    }

    //Kaikki alla olevat tietokantaoperaatiot suoritetaan korutiinissa, jotta se ei estä MainThreadin suoritusta
    //Funktiot tekevät suoritukset tietokantaan repository luokan kautta. Repository luokan käyttäminen on hyvää käytäntöä

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