package com.example.emuscle.diet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.example.emuscle.R
import com.example.emuscle.database.Diet
import com.example.emuscle.database.DietViewModel

class DietPopUp : AppCompatActivity() {

    //ExerciseViewModel alustaminen
    private lateinit var mDietViewModel: DietViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Animaatio popup näkymälle
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        setContentView(R.layout.activity_diet_pop_up)

        //Haetaan käyttöliittymän widgettien id ja asetetaan ne muuttujiin
        val dietWindowBackground = findViewById<ConstraintLayout>(R.id.diet_window_background)
        val dietWindowBorder = findViewById<CardView>(R.id.diet_window_view_with_border)
        val etTime = findViewById<EditText>(R.id.editTextTime)
        val etFood = findViewById<EditText>(R.id.editTextFood)
        val etCalories = findViewById<EditText>(R.id.editTextCalories)
        val buttonAdd = findViewById<Button>(R.id.diet_add_button)
        val title = findViewById<TextView>(R.id.diet_window_title)

        //Luodaan ViewModel DietViewModel luokasta
        mDietViewModel = ViewModelProvider(this)[DietViewModel::class.java]

        //Kun luodaan uusi ateria päivälle, otetaan day muuttuja vastaan
        val day = intent.getStringExtra("day").toString()

        //Määritetään alkuarvoksi tempId muuttujalle "null", jota käytetään tunnistamaan, mistä DietPopUp on avattu.
        //Tässä voisi käyttää state ominaisuutta
        var tempId = "null"

        //Kun päivitetään jo annettua ateriaa, otetaan alla olevat muuttujat vastaan
        tempId = intent.getStringExtra("id").toString()
        val tempDay = intent.getStringExtra("tempDay").toString()
        val tempTime = intent.getStringExtra("time").toString()
        val tempFood = intent.getStringExtra("food").toString()
        val tempCals = intent.getStringExtra("calories").toString()

        //Jos tempId ei ole "null", DietPopUp on käynnistetty RecyclerViewistä ja asetetaan tietokannasta haetut tiedot kenttiin
        if(tempId != "null") {
            buttonAdd.text = "Update"
            etTime.append(tempTime)
            etFood.append(tempFood)
            etCalories.append(tempCals)
            title.text = "Update Food"
        }

        //Suljetaan Popup ikkuna kun painetaan näkymän ulkopuolelta
        dietWindowBackground.setOnClickListener {
            onBackPressed()
        }

        dietWindowBorder.setOnClickListener {
            //Tarpeellinen funktio jos painetaan korttinäkymästä, mutta ei syöttökentästä niin popup ei sulkeudu
        }

        //Painike tietojen lähettämiseen
        buttonAdd.setOnClickListener {
            val time = etTime.text.toString()
            val food = etFood.text.toString()
            val calories = etCalories.text.toString()

            if(inputCheck(time, food, calories)) {
                //Jos tempId on "null" Luodaan uusi Diet olio ja lähetetään se tietokantaan
                if (tempDay == "null") {
                    val dietObject = Diet(0, day, time, food, calories)
                    mDietViewModel.addDiet(dietObject)
                    Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                } else {
                    //Jos tempId ei ole "null", päivitetään tietokannassa olevaa ateriaa syötetyillä tiedoilla
                    val dietObject = Diet(tempId.toInt(), tempDay, time, food, calories)
                    mDietViewModel.updateDiet(dietObject)
                    Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
                }
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
    private fun inputCheck(time: String, food: String, cals: String): Boolean {
        return !(TextUtils.isEmpty(time) || TextUtils.isEmpty(food) || TextUtils.isEmpty(cals))
    }
}