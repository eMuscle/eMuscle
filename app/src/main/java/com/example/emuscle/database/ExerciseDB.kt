package com.example.emuscle.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Exercise::class, Diet::class], version = 1, exportSchema = false)
abstract class ExerciseDB: RoomDatabase() {

    abstract fun exerciseDao(): DataAccessObject

    companion object{
        @Volatile
        private var INSTANCE: ExerciseDB? = null

        fun getDB(context: Context): ExerciseDB {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExerciseDB::class.java,
                    "exercise_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}