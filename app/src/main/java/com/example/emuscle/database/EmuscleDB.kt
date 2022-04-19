package com.example.emuscle.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Exercise::class, Diet::class, User::class], version = 1, exportSchema = false)
abstract class EmuscleDB: RoomDatabase() {

    abstract fun exerciseDao(): DataAccessObject

    companion object{
        @Volatile
        private var INSTANCE: EmuscleDB? = null

        fun getDB(context: Context): EmuscleDB {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmuscleDB::class.java,
                    "exercise_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}