package com.example.emuscle

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CalendarDay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_day)

        val id = intent.getStringExtra("id")

        val addExerciseButton = findViewById<Button>(R.id.buExercise)
        val exerciseLayout = findViewById<LinearLayout>(R.id.exercise)
        val dateText = findViewById<TextView>(R.id.date)

        dateText.text = id

        addExerciseButton.setOnClickListener{
            val exerciseView = layoutInflater.inflate(R.layout.exercise_row, null)
            val exerciseField = exerciseView.findViewById<EditText>(R.id.editTextExercise)
            exerciseLayout.addView(exerciseView)

            val db = DBController(this, null)
            val cursor = db.getData(id.toString())

            cursor!!.moveToFirst()
            Toast.makeText(this, cursor.count.toString() + " cursors", Toast.LENGTH_LONG).show()
            /*
            if(cursor.count > 0)
                exerciseField.append(cursor.getString(cursor.getColumnIndexOrThrow(DBController.NAME_COL)) + "\n")

            while(cursor.moveToNext()){
                exerciseField.append(cursor.getString(cursor.getColumnIndexOrThrow(DBController.NAME_COL)) + "\n")
            }
             */
        }

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


        //Tee funktio joka hakee tiedot databasesta mitä exercise popupissa kirjoitettiin

    }
}