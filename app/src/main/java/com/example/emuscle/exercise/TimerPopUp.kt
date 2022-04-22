package com.example.emuscle.exercise

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.emuscle.R
import kotlin.math.roundToInt


class TimerPopUp : AppCompatActivity() {

    //Alustetaan Timer muuttuja
    private lateinit var countDownTimer: CountDownTimer
    var timer = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Animaatio timerille
        overridePendingTransition(0, 0)
        setContentView(R.layout.activity_timer_pop_up)

        val popUpStartButton = findViewById<Button>(R.id.popup_window_start_button)
        val popupWindowBackground = findViewById<ConstraintLayout>(R.id.timer_window_background)
        val popupWindowBorder = findViewById<CardView>(R.id.timer_window_view_with_border)
        val timeInput = findViewById<TextView>(R.id.timeInput)
        val plusButton = findViewById<Button>(R.id.button_plus)
        val minusButton = findViewById<Button>(R.id.button_minus)
        val resetButton = findViewById<Button>(R.id.reset_button)

        // Suljetaan Popup ikkuna kun painetaan näkymän ulkopuolelta
        popupWindowBackground.setOnClickListener {
            onBackPressed()
        }
        popupWindowBorder.setOnClickListener {
            //Tarpeellinen funktio jos painetaan korttinäkymästä, mutta ei syöttökentästä niin popup ei sulkeudu
        }

        //Käynnistää timerin
        popUpStartButton.setOnClickListener {
            if(popUpStartButton.text == "Start") {
                startTimer(timeInput, popUpStartButton)
                popUpStartButton.text = "Pause"
            } else {    //Pysäyttää timerin
                pauseTimer()
                popUpStartButton.text = "Start"
            }
        }

        //Lisää 5 sekuntia timeriin
        plusButton.setOnClickListener {
            if(popUpStartButton.text == "Start")
                timeInput.text = (timeInput.text.toString().toInt() + 5).toString()
        }

        //Vähentää 5 sekuntia timerista
        minusButton.setOnClickListener {
            if(popUpStartButton.text == "Start") {
                if (timeInput.text.toString().toInt() > 5)
                    timeInput.text = (timeInput.text.toString().toInt() - 5).toString()
            }
        }

        //Resettaa timerin takaisin 30 sekuntiin
        resetButton.setOnClickListener {
            if(popUpStartButton.text == "Start") {
                timeInput.text = 30.toString()
                popUpStartButton.text = "Start"
            }
        }
    }

    //Funktio joka käynnistää timerin
    private fun startTimer(input : TextView, btn: Button) {

        timer = input.text.toString().toLong() * 1000
        countDownTimer = object : CountDownTimer(timer,1000){
            //Funktio joka suoritetaan kun ajastin menee nollaan
            override fun onFinish() {
                Toast.makeText(this@TimerPopUp,"Time up!",Toast.LENGTH_SHORT).show()
                input.text = 30.toString()
                btn.text = "Start"
            }
            //Sekunti näkymä päivittyy ruudulle joka sekunti
            override fun onTick(millisUntilFinished: Long) {
                timer = millisUntilFinished
                setTextTimer(input)
            }
        }.start()
    }

    //Funktio joka pausettaa timerin
    private fun pauseTimer() {
        countDownTimer.cancel()
    }

    //Funktio asettaa timer näkymään sekunnit
    fun setTextTimer(input : TextView) {
        val s = timer / 1000
        val format = String.format("%02d", s)
        input.text = format
    }

    //Funktio joilla saadaan oikeat mittasuhteet käyttöliittymään
    fun dpToPx(dp: Int): Int {
        val density: Float = resources
            .displayMetrics.density
        return (dp.toFloat() * density).roundToInt()
    }

    //Käynnistää animaation ja sulkee timerin
    override fun onBackPressed() {
        finish()
        overridePendingTransition(0, 0)
    }
}