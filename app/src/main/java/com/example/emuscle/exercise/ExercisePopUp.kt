package com.example.emuscle.exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.example.emuscle.R
import com.example.emuscle.database.Exercise
import com.example.emuscle.database.ExerciseViewModel

class ExercisePopUp : AppCompatActivity() {

    private lateinit var mExerciseViewModel: ExerciseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        setContentView(R.layout.activity_exercise_pop_up)
        val exerciseWindowBackground = findViewById<ConstraintLayout>(R.id.exercise_window_background)
        val exerciseWindowBorder = findViewById<CardView>(R.id.exercise_window_view_with_border)
        val addButton = findViewById<Button>(R.id.add_button)
        val enterExercise = findViewById<EditText>(R.id.editTextExercise)
        val enterSets = findViewById<EditText>(R.id.editTextSets)
        val enterReps = findViewById<EditText>(R.id.editTextReps)
        val enterWeight = findViewById<EditText>(R.id.editTextWeight)

        mExerciseViewModel = ViewModelProvider(this)[ExerciseViewModel::class.java]

        val id = intent.getStringExtra("id")

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

            if (inputCheck(exercise,sets,reps,weight)) {
                val exerciseObject = Exercise(0,id.toString(),exercise, sets, reps, weight)
                mExerciseViewModel.addExercise(exerciseObject)
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()

                onBackPressed()
            } else {
                Toast.makeText(this, "Fill out all fields!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    //Back Button activity close.
    override fun onBackPressed() {
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    private fun inputCheck(exercise: String, sets: String, reps: String, weight: String): Boolean {
        return !(TextUtils.isEmpty(exercise) || TextUtils.isEmpty(sets) || TextUtils.isEmpty(reps) || TextUtils.isEmpty(weight))
    }
}