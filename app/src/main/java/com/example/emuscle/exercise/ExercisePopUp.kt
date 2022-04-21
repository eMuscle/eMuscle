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

    //ExerciseViewModel muuttujan luominen (?)
    private lateinit var mExerciseViewModel: ExerciseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Animaatio popup näkymälle
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        setContentView(R.layout.activity_exercise_pop_up)

        //Vastaanotetaan päivämäärän id
        val id = intent.getStringExtra("id")

        //Haetaan käyttöliittymän widgettien id ja asetetaan ne muuttujiin
        val exerciseWindowBackground = findViewById<ConstraintLayout>(R.id.exercise_window_background)
        val exerciseWindowBorder = findViewById<CardView>(R.id.exercise_window_view_with_border)
        val addButton = findViewById<Button>(R.id.add_button)
        val enterExercise = findViewById<EditText>(R.id.editTextExercise)
        val enterSets = findViewById<EditText>(R.id.editTextSets)
        val enterReps = findViewById<EditText>(R.id.editTextReps)
        val enterWeight = findViewById<EditText>(R.id.editTextWeight)

        //Luodaan ViewModel ExerciseViewModel luokasta
        mExerciseViewModel = ViewModelProvider(this)[ExerciseViewModel::class.java]

        // Suljetaan Popup ikkuna kun painetaan näkymän ulkopuolelta
        exerciseWindowBackground.setOnClickListener {
            onBackPressed()
        }

        exerciseWindowBorder.setOnClickListener {
            //Tarpeellinen funktio jos painetaan korttinäkymästä, mutta ei syöttökentästä niin popup ei sulkeudu
        }

        //Painike tietojen lähettämiseen
        addButton.setOnClickListener{
            val exercise = enterExercise.text.toString()
            val sets = enterSets.text.toString()
            val reps = enterReps.text.toString()
            val weight = enterWeight.text.toString()

            //Tarkastaa onko kaikki tekstikentät täytetty
            if (inputCheck(exercise,sets,reps,weight)) {

                //Muuttuja johon menee syötetyt arvot joka lähetetään tietokantaan
                val exerciseObject = Exercise(0,id.toString(),exercise, sets, reps, weight)
                mExerciseViewModel.addExercise(exerciseObject)
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                onBackPressed()

            } else {
                //Virheviesti jos kenttiä ei ole täytetty
                Toast.makeText(this, "Fill out all fields!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    //Käynnistää animaation ja sulkee popupin
    override fun onBackPressed() {
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    //Tarkastus funktio syöttökentille
    private fun inputCheck(exercise: String, sets: String, reps: String, weight: String): Boolean {
        return !(TextUtils.isEmpty(exercise) || TextUtils.isEmpty(sets) || TextUtils.isEmpty(reps) || TextUtils.isEmpty(weight))
    }
}