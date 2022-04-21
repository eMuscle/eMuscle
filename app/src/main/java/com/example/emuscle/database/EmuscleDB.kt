package com.example.emuscle.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//Tietokannan luonti. Entities ottaa vastaan kaikki tietokannan käyttämät taulut
@Database(entities = [Exercise::class, Diet::class, User::class], version = 1)
abstract class EmuscleDB: RoomDatabase() {

    abstract fun exerciseDao(): DataAccessObject

    companion object{
        @Volatile
        private var INSTANCE: EmuscleDB? = null

        //Katsoo onko tietokanta luotu. Jos ei ole, se luodaan ja jos tietokanta on jo luotu, sovellus käyttää sitä
        fun getInstance(context: Context): EmuscleDB =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        //Funktio luo tietokannan
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                    context.applicationContext,
                    EmuscleDB::class.java,
                    "emuscle_db"
                ).addCallback(object: Callback() {
                    //Tietokannan luonnin yhteydessä tehdään tyhjä merkintä User tauluun, jotta sovellus ei kaadu ensimmäistä kertaa käynnistäessä
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        CoroutineScope(Dispatchers.IO).launch {
                            getInstance(context).exerciseDao().addUser(PREPOPULATE_DATA)
                        }
                    }
                }).build()

        val PREPOPULATE_DATA = User(0,"","","")
    }
}