package com.example.emuscle.diet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import com.example.emuscle.R

//import kotlinx.android.synthetic.main.activity_calendar_diet.*

class CalendarDiet : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        setContentView(R.layout.activity_calendar_diet)
        val calendarDietView = findViewById<CalendarView>(R.id.calendarDietView)

        //Tarkkailee kalenterin klikattua päivää ja tekee siitä päivämäärästä id:n ja aukaisee sen päivämäärän diet näkymän
        calendarDietView.setOnDateChangeListener { calendar, year, mont, day ->
            val intent = Intent(this,  CalendarDietDay::class.java)
            val month = mont + 1
            val id = "$day/$month/$year"
            intent.putExtra("id", id)
            startActivity(intent)
        }
    }

    //Käynnistää animaation
    override fun onBackPressed() {
        finish()
        overridePendingTransition(0, 0)
    }
}