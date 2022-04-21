package com.example.emuscle.database

//Väliluokka, joka ohjaa UserViewModelissa olevat funktiot tietokantaan
class UserRepository (private val dataAccessObject: DataAccessObject) {

    suspend fun addUser(user: User) {
        dataAccessObject.addUser(user)
    }

    fun getUserData(): User {
        return dataAccessObject.getLastRecordUser()
    }

}