package com.example.emuscle

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        val loginButton = findViewById<Button>(R.id.buLogin)

        loginButton.setOnClickListener{
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
        }
    }



}