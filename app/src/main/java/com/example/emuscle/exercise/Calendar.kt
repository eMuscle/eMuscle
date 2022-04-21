package com.example.emuscle.exercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import com.example.emuscle.R

class Calendar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        //Haetaan käyttöliittymän kalenterin id ja asetetaan se muuttujaan
        val calendar = findViewById<CalendarView>(R.id.calendarView)

        //Tarkkailee kalenterin klikattua päivää ja tekee siitä päivämäärästä id:n ja aukaisee sen päivämäärän exercise näkymän
        calendar.setOnDateChangeListener { calendar, year, mont, day ->
            val intent = Intent(this,  CalendarDay::class.java)
            val month = mont + 1
            val id = "$day/$month/$year"
            //Lähettää päivän id:n arvon stringinä seuraavalle näkymälle
            intent.putExtra("id", id)
            startActivity(intent)
        }
    }
}