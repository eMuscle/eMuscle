package com.example.emuscle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.example.emuscle.database.DietViewModel


class MainActivity : AppCompatActivity() {

    //Handler muuttujan ja DietViewModel database muuttujan luominen
    lateinit var handler: Handler
    private lateinit var mDietViewModel: DietViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Piilottaa notifikaatio barin
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Tietokannan alustus DietViewModelin avulla
        mDietViewModel = ViewModelProvider(this)[DietViewModel::class.java]
        mDietViewModel.getDietByDay("").observe(this) { diet ->

        }

        //Intro logo 2 sekuntia näkyvissä ennenkuin käynnistetään mainmenu
        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
            finish()
        }, 2000)

    }
}