package com.example.emuscle.profile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.emuscle.R

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val heightField = findViewById<EditText>(R.id.editTextPituus)
        val weightField = findViewById<EditText>(R.id.editTextPaino)
        val bmiField = findViewById<TextView>(R.id.textViewBMI)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            if(heightField.text.isNotEmpty() && weightField.text.isNotEmpty()) {
                val height = heightField.text.toString().toFloat()/100
                val weight = weightField.text.toString().toFloat()

                val bmi = weight / (height * height)
                bmiField.text = String.format("%.2f", bmi)
            }
        }

    }
}