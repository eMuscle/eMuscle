package com.example.emuscle.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val repository: UserRepository

    init {
        val dataAccessObject = EmuscleDB.getInstance(application).exerciseDao()
        repository = UserRepository(dataAccessObject)
    }

    fun getUserData(): User {
        return repository.getUserData()
    }

    //Kaikki alla olevat tietokantaoperaatiot suoritetaan korutiinissa, jotta se ei estä MainThreadin suoritusta
    //Funktiot tekevät suoritukset tietokantaan repository luokan kautta. Repository luokan käyttäminen on hyvää käytäntöä

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

}