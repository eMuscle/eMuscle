package com.example.emuscle

import android.os.*
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.Chronometer.OnChronometerTickListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class CalendarDay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_day)
        var isWorking = false
        var timerStartTime = 10000
        var lastPause = (-timerStartTime).toLong()
        val id = intent.getStringExtra("id")

        val dateText = findViewById<TextView>(R.id.date)
        dateText.text = id

        val btnStartStop = findViewById<Button>(R.id.buTimer)
        val timer = findViewById<Chronometer>(R.id.view_timer)

        timer.onChronometerTickListener = OnChronometerTickListener { chronometer ->
            val elapsed = -(SystemClock.elapsedRealtime() - chronometer.base)
            if (elapsed <= 0) {
                chronometer.stop()
                isWorking = false
                dateText.text = elapsed.toString()
                btnStartStop.text = if(isWorking) "stop" else "start"

                lastPause = -timerStartTime.toLong()
                timer.base = SystemClock.elapsedRealtime() - lastPause
                //Set vibration or sound

            }
        }

        btnStartStop?.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View) {
                if (!isWorking)  {
                    timer.base = SystemClock.elapsedRealtime() - lastPause
                    timer.start()
                    isWorking = true
                } else {
                    lastPause = SystemClock.elapsedRealtime() - timer.base
                    timer.stop()
                    isWorking = false
                }

                btnStartStop.text = if(isWorking) "stop" else "start"
            }
        })
    }
}