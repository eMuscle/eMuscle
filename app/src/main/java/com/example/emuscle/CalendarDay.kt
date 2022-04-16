package com.example.emuscle

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.emuscle.database.ExerciseViewModel

class CalendarDay : AppCompatActivity() {
    private lateinit var mExerciseViewModel: ExerciseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_day)

        val id = intent.getStringExtra("id").toString()

        val dateText = findViewById<TextView>(R.id.date)

        //RecyclerView setup
        val adapter = ListAdapter()
        val exerciseLayout = findViewById<RecyclerView>(R.id.recycler_view)
        exerciseLayout.adapter = adapter
        exerciseLayout.layoutManager = LinearLayoutManager(this)

        //ExerciseViewModel
        mExerciseViewModel = ViewModelProvider(this)[ExerciseViewModel::class.java]
        mExerciseViewModel.getExercisesByDay(id).observe(this) { exercise ->
            adapter.setData(exercise)
        }

        dateText.text = id

        val buttonExercise = findViewById<Button>(R.id.buttonForExercise)
        buttonExercise.setOnClickListener{
            val intent = Intent(this, ExercisePopUp::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }

        val timerPopup = findViewById<Button>(R.id.timerbutton)
        timerPopup.setOnClickListener {
            val intent = Intent(this, TimerPopUp::class.java)
            startActivity(intent)
        }


    }
}