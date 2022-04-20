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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Profile : AppCompatActivity() {
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val heightField = findViewById<EditText>(R.id.editTextPituus)
        val weightField = findViewById<EditText>(R.id.editTextPaino)
        val bmiField = findViewById<TextView>(R.id.textViewBMI)
        val btnSave = findViewById<Button>(R.id.btnSave)

        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]


        CoroutineScope(Dispatchers.IO).launch {
            val userData = mUserViewModel.getUserData()
            weightField.append(userData.weight)
            heightField.append(userData.height)

            if(heightField.text.isNotEmpty() && weightField.text.isNotEmpty()) {
                val height = heightField.text.toString().toFloat()/100
                val weight = weightField.text.toString()

                val bmi = weight.toFloat() / (height * height)
                bmiField.text = String.format("%.2f", bmi)
            }
        }


        btnSave.setOnClickListener {
            if(heightField.text.isNotEmpty() && weightField.text.isNotEmpty()) {
                val height = heightField.text.toString().toFloat()/100
                val weight = weightField.text.toString()

                val bmi = weight.toFloat() / (height * height)
                bmiField.text = String.format("%.2f", bmi)

                val userObject = User(0, height.toString(), weight,14.toString())
                mUserViewModel.addUser(userObject)
            }
        }

    }
}