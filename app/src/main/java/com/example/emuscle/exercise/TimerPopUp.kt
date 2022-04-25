package com.example.emuscle.exercise

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.NotificationCompat
import com.example.emuscle.R


class TimerPopUp : AppCompatActivity() {

    //Alustetaan Timer muuttuja
    private lateinit var countDownTimer: CountDownTimer
    var timer = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Animaatio timerille
        overridePendingTransition(0, 0)
        setContentView(R.layout.activity_timer_pop_up)
        val day = intent.getStringExtra("id").toString()

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
                startTimer(timeInput, popUpStartButton, day)
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
    private fun startTimer(input : TextView, btn: Button, day: String) {

        timer = input.text.toString().toLong() * 1000
        countDownTimer = object : CountDownTimer(timer,1000){
            //Funktio joka suoritetaan kun ajastin menee nollaan
            override fun onFinish() {
                Toast.makeText(this@TimerPopUp,"Time up!",Toast.LENGTH_SHORT).show()
                addNotification(day)
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

    //Käynnistää animaation ja sulkee timerin
    override fun onBackPressed() {
        finish()
        overridePendingTransition(0, 0)
    }

    private fun addNotification(day: String) {
        val channelID = "eMuscleChannel"
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationChannel = NotificationChannel(channelID, "test", NotificationManager.IMPORTANCE_HIGH)
        notificationChannel.enableLights(true)
        notificationChannel.enableVibration(false)
        manager.createNotificationChannel(notificationChannel)

        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this, channelID)
            .setSmallIcon(R.drawable.ic_alarm) //set icon for notification
            .setContentTitle("Timer") //set title of notification
            .setContentText("Time to lift some iron!") //this is notification message
            .setAutoCancel(true) // makes auto cancel of notification
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) //set priority of notification

        val notificationIntent = Intent(this, CalendarDay::class.java)
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        notificationIntent.putExtra("day", day)

        val pendingIntent = PendingIntent.getActivity(
            this, 0, notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        builder.setContentIntent(pendingIntent)


        // Add as notification
        manager.notify(0, builder.build())
    }
}