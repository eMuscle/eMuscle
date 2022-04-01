package com.example.emuscle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        val btnCalendar = findViewById<Button>(R.id.buCalendar)

        btnCalendar.setOnClickListener{
            val intent = Intent(this, Calendar::class.java)
            startActivity(intent)
        }
    }
}