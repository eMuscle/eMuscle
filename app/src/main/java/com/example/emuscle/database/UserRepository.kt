package com.example.emuscle.database

import androidx.lifecycle.LiveData

class UserRepository (private val dataAccessObject: DataAccessObject) {

    suspend fun addUser(user: User) {
        dataAccessObject.addUser(user)
    }

    fun getUserData(): User {
        return dataAccessObject.getLastRecordUser()
    }

}