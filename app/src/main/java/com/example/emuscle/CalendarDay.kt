package com.example.emuscle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class CalendarDay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_day)

        val id = intent.getStringExtra("id")

        val addExerciseButton = findViewById<Button>(R.id.buExercise)
        val exerciseLayout = findViewById<LinearLayout>(R.id.exercise)
        val dateText = findViewById<TextView>(R.id.date)


        dateText.text = id

        addExerciseButton.setOnClickListener{
            val exerciseView = layoutInflater.inflate(R.layout.exercise_row, null)
            exerciseLayout.addView(exerciseView)
        }

        val buttonExercise = findViewById<Button>(R.id.buttonForExercise)
        buttonExercise.setOnClickListener{
            val intent = Intent(this, ExercisePopUp::class.java)
            startActivity(intent)
        }

        val timerPopup = findViewById<Button>(R.id.timerbutton)
        timerPopup.setOnClickListener {
            val intent = Intent(this, TimerPopUp::class.java)
            startActivity(intent)
        }

        //Tee funktio joka hakee tiedot databasesta mitä exercise popupissa kirjoitettiin

    }
}