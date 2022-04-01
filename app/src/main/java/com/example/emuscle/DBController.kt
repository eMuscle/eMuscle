package com.example.emuscle

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBController(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names
        // along with their data types is given
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                NAME_COL + " TEXT" +
                SETS_COL + " TEXT" +
                REPS_COL + " TEXT" +
                KG_COL + " TEXT" +")")

        // we are calling sqlite
        // method for executing our query
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // This method is for adding data in our database
    fun addName(name : String, sets : String, reps : String, weight : String ){

        val values = ContentValues()

        values.put(NAME_COL, name)
        values.put(SETS_COL, sets)
        values.put(REPS_COL, reps)
        values.put(KG_COL, weight)

        val db = this.writableDatabase

        // all values are inserted into database
        db.insert(TABLE_NAME, null, values)

        db.close()
    }

    // get all data from our database
    fun getData(id : String): Cursor? {
        val db = this.readableDatabase

        // below code returns a cursor to
        // read data from the database
        return db.rawQuery("SELECT * FROM $TABLE_NAME WHERE id=$id", null)

    }

    companion object{
        // here we have defined variables for our database
        private const val DATABASE_NAME = "EMUSCLE_DB"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "data"
        const val ID_COL = "id"
        const val NAME_COL = "name"
        const val SETS_COL = "sets"
        const val REPS_COL = "reps"
        const val KG_COL = "weight"
    }
}