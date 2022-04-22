package com.example.emuscle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.emuscle.diet.CalendarDiet
import com.example.emuscle.exercise.Calendar
import com.example.emuscle.profile.Profile

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        //Haetaan käyttöliittymän widgeteiden id:t ja asetetaan ne muuttujiin
        val btnCalendar = findViewById<Button>(R.id.buCalendar)
        val btnDiet = findViewById<Button>(R.id.buDiet)
        val btnProfile = findViewById<Button>(R.id.buProfile)

        //Avaa exercise kalenteri näkymän käyttöliittymään
        btnCalendar.setOnClickListener{
            val intent = Intent(this, Calendar::class.java)
            startActivity(intent)
        }

        //Avaa diet kalenteri näkymän käyttöliittymään
        btnDiet.setOnClickListener{
            val intent = Intent(this, CalendarDiet::class.java)
            startActivity(intent)
        }

        //Avaa profile näkymän käyttöliittymään
        btnProfile.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
    }
}