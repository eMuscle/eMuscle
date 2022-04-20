package com.example.emuscle

import android.content.Intent
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.example.emuscle.database.DietViewModel
import com.example.emuscle.database.EmuscleDB
import com.example.emuscle.database.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var handler: Handler
    private lateinit var mDietViewModel: DietViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Hides the notification bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //This is initialized here to create database now rather than later
        mDietViewModel = ViewModelProvider(this)[DietViewModel::class.java]
        mDietViewModel.getDietByDay("").observe(this) { diet ->

        }

        //This shows the splash screen 2 seconds before moving to next intent
        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
            finish()
        }, 2000)

    }
}