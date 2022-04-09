package com.example.emuscle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout

class ExercisePopUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_pop_up)
        val exerciseWindowBackground = findViewById<ConstraintLayout>(R.id.exercise_window_background)
        val exerciseWindowBorder = findViewById<CardView>(R.id.exercise_window_view_with_border)
        val addButton = findViewById<Button>(R.id.add_button)
        val enterExercise = findViewById<EditText>(R.id.editTextExercise)
        val enterSets = findViewById<EditText>(R.id.editTextSets)
        val enterReps = findViewById<EditText>(R.id.editTextReps)
        val enterWeight = findViewById<EditText>(R.id.editTextWeight)

        val id = intent.getStringExtra("id")
        val db = DBController(this, null)

        // Hide status bar
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Close the Popup Window when you press outside of CardView
        exerciseWindowBackground.setOnClickListener {
            onBackPressed()
        }

        exerciseWindowBorder.setOnClickListener {
            //This is empty but necessary so that when clicked on CardView, it doesn't close
        }

        addButton.setOnClickListener{

            val exercise = enterExercise.text.toString()
            val sets = enterSets.text.toString()
            val reps = enterReps.text.toString()
            val weight = enterWeight.text.toString()

            db.addExercise(id.toString(), exercise, sets, reps, weight)

            enterExercise.text.clear()
            enterSets.text.clear()
            enterReps.text.clear()
            enterWeight.text.clear()

            onBackPressed()
        }
    }
    
    //Back Button activity close.
    override fun onBackPressed() {
        finish()
        overridePendingTransition(0, 0)
    }
}