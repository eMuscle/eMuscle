package com.example.emuscle

import android.os.Bundle
import android.os.CountDownTimer
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout


class TimerPopUp : AppCompatActivity() {

    private val start = 60000L
    var timer = start
    private lateinit var countDownTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        setContentView(R.layout.activity_timer_pop_up)
        val popUpStartButton = findViewById<Button>(R.id.popup_window_start_button)
        val popupWindowBackground = findViewById<ConstraintLayout>(R.id.timer_window_background)
        val popupWindowBorder = findViewById<CardView>(R.id.timer_window_view_with_border)
        val timeInput = findViewById<TextView>(R.id.timeInput)
        val plusButton = findViewById<Button>(R.id.button_plus)
        val minusButton = findViewById<Button>(R.id.button_minus)
        val resetButton = findViewById<Button>(R.id.reset_button)

        // Hide status bar
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Close the Popup Window when you press outside of CardView
        popupWindowBackground.setOnClickListener {
            onBackPressed()
        }
        popupWindowBorder.setOnClickListener {
            //This is empty but necessary so that when clicked on CardView, it doesn't close
        }

        popUpStartButton.setOnClickListener {
            if(popUpStartButton.text == "Start") {
                startTimer(timeInput, popUpStartButton)
                popUpStartButton.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 180)
                popUpStartButton.text = "Pause"
            } else {
                pauseTimer()
                popUpStartButton.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 180)
                popUpStartButton.text = "Start"
            }
        }
        plusButton.setOnClickListener {
            if(popUpStartButton.text == "Start")
                timeInput.text = (timeInput.text.toString().toInt() + 5).toString()
        }
        minusButton.setOnClickListener {
            if(popUpStartButton.text == "Start") {
                if (timeInput.text.toString().toInt() > 5)
                    timeInput.text = (timeInput.text.toString().toInt() - 5).toString()
            }
        }

        resetButton.setOnClickListener {
            timeInput.text = 30.toString()
            popUpStartButton.text = "Start"
        }

    }

    private fun startTimer(input : TextView, btn: Button) {
        timer = input.text.toString().toLong() * 1000

        countDownTimer = object : CountDownTimer(timer,1000){
            //end of timer
            override fun onFinish() {
                Toast.makeText(this@TimerPopUp,"Time to work!",Toast.LENGTH_SHORT).show()
                input.text = 30.toString()
                btn.text = "Start"
                btn.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 180)
            }

            override fun onTick(millisUntilFinished: Long) {
                timer = millisUntilFinished
                setTextTimer(input)
            }
        }.start()
    }

    private fun pauseTimer() {
        countDownTimer.cancel()
    }

    fun setTextTimer(input : TextView) {

        val s = (timer / 1000)
        val format = String.format("%02d", s)

        input.text = format
    }



    //Back Button activity close.
    override fun onBackPressed() {
        finish()
        overridePendingTransition(0, 0)
    }


}