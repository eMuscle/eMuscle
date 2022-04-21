package com.example.emuscle.exercise

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.example.emuscle.R
import com.example.emuscle.database.Exercise
import com.example.emuscle.database.ExerciseViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ExerciseUpdate : AppCompatActivity() {

    //ExerciseViewModel muuttujan luominen
    private lateinit var mExerciseViewModel: ExerciseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Animaatio Edit popup näkymälle
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        setContentView(R.layout.activity_exercise_update)

        //Haetaan käyttöliittymän widgettien id ja asetetaan ne muuttujiin
        val exerciseUpdateWindowBackground = findViewById<ConstraintLayout>(R.id.exerciseUpdate_window_background)
        val exerciseUpdateWindowBorder = findViewById<CardView>(R.id.exerciseUpdate_window_view_with_border)
        val updateButton = findViewById<Button>(R.id.update_button)
        val deleteButton = findViewById<FloatingActionButton>(R.id.deleteButton)
        val updateExercise = findViewById<EditText>(R.id.editTextExerciseUpdate)
        val updateSets = findViewById<EditText>(R.id.editTextSetsUpdate)
        val updateReps = findViewById<EditText>(R.id.editTextRepsUpdate)
        val updateWeight = findViewById<EditText>(R.id.editTextWeightUpdate)

        //Luodaan ViewModel ExerciseViewModel luokasta
        mExerciseViewModel = ViewModelProvider(this)[ExerciseViewModel::class.java]

        //Hakee exercise tietokannasta tiedot exercise dayn käyttöliittymään
        updateExercise.append(intent.getStringExtra("exercise").toString())
        updateSets.append(intent.getStringExtra("sets").toString())
        updateReps.append(intent.getStringExtra("reps").toString())
        updateWeight.append(intent.getStringExtra("weight").toString())

        //Painetun harjoituksen id ja päivä
        val id = intent.getStringExtra("id").toString()
        val day = intent.getStringExtra("day").toString()

        // Suljetaan Popup ikkuna kun painetaan näkymän ulkopuolelta
        exerciseUpdateWindowBackground.setOnClickListener {
            onBackPressed()
        }

        exerciseUpdateWindowBorder.setOnClickListener {
            //Tarpeellinen funktio jos painetaan korttinäkymästä, mutta ei syöttökentästä niin popup ei sulkeudu
        }

        //Update painike päivittää valmiiksi syötetyn harjoituksen
        updateButton.setOnClickListener {
            val exercise = updateExercise.text.toString()
            val sets = updateSets.text.toString()
            val reps = updateReps.text.toString()
            val weight = updateWeight.text.toString()

            //Tarkastaa onko kaikkiin kenttiin syötetty tieto
            if (inputCheck(exercise,sets,reps,weight)) {
                //Luodaan Exercise olio joka lähetetään tietokantaan syötetyillä arvoilla
                val updatedExercise = Exercise(id.toInt(), day, exercise, sets, reps, weight)
                mExerciseViewModel.updateExercise(updatedExercise)
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                onBackPressed()

            } else {
                //Virheviesti jos kenttiä ei ole täytetty
                Toast.makeText(this, "Fill out all fields!", Toast.LENGTH_SHORT).show()
            }
        }

        //Poisto painike joka poistaa valitun harjoituksen id:n perusteella
        deleteButton.setOnClickListener {
            onBackPressed()
            mExerciseViewModel.deleteExercise(id)
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