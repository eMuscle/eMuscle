package com.example.emuscle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class CalendarDay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_day)

        val id = intent.getStringExtra("id")

        val dateText = findViewById<TextView>(R.id.date)
        dateText.text = id

        val popup = findViewById<Button>(R.id.timerbutton)
        popup.setOnClickListener {
            val intent = Intent(this, TimerPopUp::class.java)

            startActivity(intent)
        }
    }
}