package com.example.emuscle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout

class ExercisePopUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_pop_up)
        val exerciseWindowBackground = findViewById<ConstraintLayout>(R.id.exercise_window_background)
        val exerciseWindowBorder = findViewById<CardView>(R.id.exercise_window_view_with_border)

        // Hide status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Close the Popup Window when you press outside of CardView
        exerciseWindowBackground.setOnClickListener {
            onBackPressed()
        }

        exerciseWindowBorder.setOnClickListener {
            //This is empty but necessary so that when clicked on CardView, it doesn't close
        }
    }



    //Back Button activity close.
    override fun onBackPressed() {
        finish()
        overridePendingTransition(0, 0)
    }
}