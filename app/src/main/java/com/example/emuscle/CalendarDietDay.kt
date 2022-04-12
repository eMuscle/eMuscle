package com.example.emuscle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class CalendarDietDay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_diet_day)

        val id = intent.getStringExtra("id")
        val dateText = findViewById<TextView>(R.id.dateDiet)
        dateText.text = id

        val buttonDiet = findViewById<Button>(R.id.buDietPopUp)
        buttonDiet.setOnClickListener{
            val intent = Intent(this, DietPopUp::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }
    }
}