package com.example.emuscle.profile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.emuscle.R
import com.example.emuscle.database.User
import com.example.emuscle.database.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Profile : AppCompatActivity() {

    //User Database muuttujan luominen
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        //Haetaan käyttöliittymän widgeteiden id:t ja asetetaan ne muuttujiin
        val heightField = findViewById<EditText>(R.id.editTextPituus)
        val weightField = findViewById<EditText>(R.id.editTextPaino)
        val bmiField = findViewById<TextView>(R.id.textViewBMI)
        val bodyFatField = findViewById<EditText>(R.id.editTextBodyFat)
        val btnSave = findViewById<Button>(R.id.btnSave)

        //ViewModelProvider luo ViewModel-ilmentymän näkyville (?)
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        //Tekee Coroutine toiminnon user datan hakemisesta käyttöliittymään
        CoroutineScope(Dispatchers.IO).launch {
            val userData = mUserViewModel.getUserData()
            weightField.append(userData.weight)
            heightField.append(userData.height)
            bodyFatField.append(userData.bodyFat)

            //Tarkastaa pituus ja paino kentistä onko ne tyhjät ja pyöristää painoindeksin
            if(heightField.text.isNotEmpty() && weightField.text.isNotEmpty()) {
                val height = heightField.text.toString().toFloat()/100
                val weight = weightField.text.toString()

                val bmi = weight.toFloat() / (height * height)
                bmiField.text = String.format("%.2f", bmi)
            }
        }

        //Save painike lähettää syötetyt arvot tietokantaan
        btnSave.setOnClickListener {
            if(heightField.text.isNotEmpty() && weightField.text.isNotEmpty()) {
                val height = heightField.text.toString()
                val weight = weightField.text.toString()
                val bodyFat = bodyFatField.text.toString()

                //Laskee ja näyttää painoindeksin käyttöliittymässä
                val bmi = weight.toFloat() / (height.toFloat()/100 * height.toFloat()/100)
                bmiField.text = String.format("%.2f", bmi)

                val userObject = User(0, height, weight, bodyFat)
                mUserViewModel.addUser(userObject)
            }
        }

    }
}