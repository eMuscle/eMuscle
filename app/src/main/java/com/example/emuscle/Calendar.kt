package com.example.emuscle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView

class Calendar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        val calendar = findViewById<CalendarView>(R.id.calendarView)
        calendar.setOnDateChangeListener { calendar, year, mont, day ->
            val intent = Intent(this,  CalendarDay::class.java)
            val month = mont + 1
            val id = "$day/$month/$year"
            intent.putExtra("id", id)
            startActivity(intent)
        }
    }
}