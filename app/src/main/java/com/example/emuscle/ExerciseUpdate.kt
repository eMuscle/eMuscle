package com.example.emuscle

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.example.emuscle.database.Exercise
import com.example.emuscle.database.ExerciseViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ExerciseUpdate : AppCompatActivity() {

    private lateinit var mExerciseViewModel: ExerciseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_update)

        val exerciseUpdateWindowBackground = findViewById<ConstraintLayout>(R.id.exerciseUpdate_window_background)
        val exerciseUpdateWindowBorder = findViewById<CardView>(R.id.exerciseUpdate_window_view_with_border)
        val updateButton = findViewById<Button>(R.id.update_button)
        val deleteButton = findViewById<FloatingActionButton>(R.id.deleteButton)
        val updateExercise = findViewById<EditText>(R.id.editTextExerciseUpdate)
        val updateSets = findViewById<EditText>(R.id.editTextSetsUpdate)
        val updateReps = findViewById<EditText>(R.id.editTextRepsUpdate)
        val updateWeight = findViewById<EditText>(R.id.editTextWeightUpdate)

        mExerciseViewModel = ViewModelProvider(this)[ExerciseViewModel::class.java]

        updateExercise.append(intent.getStringExtra("exercise").toString())
        updateSets.append(intent.getStringExtra("sets").toString())
        updateReps.append(intent.getStringExtra("reps").toString())
        updateWeight.append(intent.getStringExtra("weight").toString())

        val id = intent.getStringExtra("id").toString()
        val day = intent.getStringExtra("day").toString()

        exerciseUpdateWindowBackground.setOnClickListener {
            onBackPressed()
        }

        exerciseUpdateWindowBorder.setOnClickListener {
            //This is empty but necessary so that when clicked on CardView, it doesn't close
        }

        updateButton.setOnClickListener {
            val exercise = updateExercise.text.toString()
            val sets = updateSets.text.toString()
            val reps = updateReps.text.toString()
            val weight = updateWeight.text.toString()

            if (inputCheck(exercise,sets,reps,weight)) {
                val updatedExercise = Exercise(id.toInt(), day, exercise, sets, reps, weight)
                mExerciseViewModel.updateExercise(updatedExercise)
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()

                onBackPressed()
            } else {
                Toast.makeText(this, "Fill out all fields!", Toast.LENGTH_SHORT).show()
            }
        }

        deleteButton.setOnClickListener {
            onBackPressed()
            mExerciseViewModel.deleteExercise(id)
        }
    }

    private fun inputCheck(exercise: String, sets: String, reps: String, weight: String): Boolean {
        return !(TextUtils.isEmpty(exercise) || TextUtils.isEmpty(sets) || TextUtils.isEmpty(reps) || TextUtils.isEmpty(weight))
    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition(0, 0)
    }
}