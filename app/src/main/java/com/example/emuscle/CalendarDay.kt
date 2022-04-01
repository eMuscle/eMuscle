package com.example.emuscle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView

class CalendarDay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_day)
        val id = intent.getStringExtra("id")

        val dateText = findViewById<TextView>(R.id.date)
        dateText.text = id

        val btnStartStop = findViewById<Button>(R.id.buTimer)
        val timer = findViewById<Chronometer>(R.id.view_timer)



        btnStartStop?.setOnClickListener(object: View.OnClickListener {
            var isWorking = false
            var lastPause = 0.0.toLong()
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