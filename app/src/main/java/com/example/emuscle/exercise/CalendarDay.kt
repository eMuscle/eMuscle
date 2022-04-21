package com.example.emuscle.exercise

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.emuscle.R
import com.example.emuscle.database.ExerciseViewModel

class CalendarDay : AppCompatActivity() {

    //Exercise database muuttujan luominen
    private lateinit var mExerciseViewModel: ExerciseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_day)

        //Vastaanottaa stringinä päiväkohtaisen id:n arvon
        val id = intent.getStringExtra("id").toString()

        //Haetaan käyttöliittymän widgettien id ja asetetaan ne muuttujiin
        val dateText = findViewById<TextView>(R.id.date)
        val buttonExercise = findViewById<Button>(R.id.buttonForExercise)
        val timerPopup = findViewById<Button>(R.id.timerbutton)

        //RecyclerViewin alustus
        //Luodaan muuttuja adapter joka on tyyppiä ListAdapter
        val adapter = ListAdapter()
        val exerciseLayout = findViewById<RecyclerView>(R.id.recycler_view)
        //RecyclerViewille määritetään adapter
        exerciseLayout.adapter = adapter
        exerciseLayout.layoutManager = LinearLayoutManager(this)

        //Luodaan ViewModel ExerciseViewModel luokasta
        mExerciseViewModel = ViewModelProvider(this)[ExerciseViewModel::class.java]
        mExerciseViewModel.getExercisesByDay(id).observe(this) { exercise ->
            adapter.setData(exercise)
        }

        //Vastaanotetun päivämäärän id string asetetaan teksti widgettiin
        dateText.text = id

        //Avaa Exercise popup näkymän
        buttonExercise.setOnClickListener{
            val intent = Intent(this, ExercisePopUp::class.java)
            //Vie päivämäärä id:tä Exercise popup näkymälle
            intent.putExtra("id", id)
            startActivity(intent)
        }

        //Avaa Timer popup näkymän
        timerPopup.setOnClickListener {
            val intent = Intent(this, TimerPopUp::class.java)
            startActivity(intent)
        }

    }
}