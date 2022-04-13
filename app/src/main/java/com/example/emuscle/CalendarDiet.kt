package com.example.emuscle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
//import kotlinx.android.synthetic.main.activity_calendar_diet.*

class CalendarDiet : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_diet)
        val calendarDietView = findViewById<CalendarView>(R.id.calendarDietView)
        calendarDietView.setOnDateChangeListener { calendar, year, mont, day ->
            val intent = Intent(this,  CalendarDietDay::class.java)
            val month = mont + 1
            val id = "$day/$month/$year"
            intent.putExtra("id", id)
        startActivity(intent)
        }
    }
}