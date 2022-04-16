package com.example.emuscle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.example.emuscle.database.Diet
import com.example.emuscle.database.DietViewModel

class DietPopUp : AppCompatActivity() {
    private lateinit var mDietViewModel: DietViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        setContentView(R.layout.activity_diet_pop_up)

        val dietWindowBackground = findViewById<ConstraintLayout>(R.id.diet_window_background)
        val dietWindowBorder = findViewById<CardView>(R.id.diet_window_view_with_border)
        val etTime = findViewById<EditText>(R.id.editTextTime)
        val etFood = findViewById<EditText>(R.id.editTextFood)
        val etCalories = findViewById<EditText>(R.id.editTextCalories)
        val buttonAdd = findViewById<Button>(R.id.diet_add_button)


        val day = intent.getStringExtra("day").toString()
        mDietViewModel = ViewModelProvider(this)[DietViewModel::class.java]

        var tempId = "null"
        tempId = intent.getStringExtra("id").toString()
        val tempDay = intent.getStringExtra("tempDay").toString()
        val tempTime = intent.getStringExtra("time").toString()
        val tempFood = intent.getStringExtra("food").toString()
        val tempCals = intent.getStringExtra("calories").toString()

        if(tempId != "null") {
            buttonAdd.text = "Update"
            etTime.append(tempTime)
            etFood.append(tempFood)
            etCalories.append(tempCals)
        }

        dietWindowBackground.setOnClickListener {
            onBackPressed()
        }

        dietWindowBorder.setOnClickListener {
            //This is empty but necessary so that when clicked on CardView, it doesn't close
        }

        buttonAdd.setOnClickListener {
            val time = etTime.text.toString()
            val food = etFood.text.toString()
            val calories = etCalories.text.toString()

            if(inputCheck(time, food, calories)) {
                if (tempDay == "null") {
                    val dietObject = Diet(0, day, time, food, calories)
                    mDietViewModel.addDiet(dietObject)
                    Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                } else {
                    val dietObject = Diet(tempId.toInt(), tempDay, time, food, calories)
                    mDietViewModel.updateDiet(dietObject)
                    Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
                }
                onBackPressed()
            } else {
                Toast.makeText(this, "Fill out all fields!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    private fun inputCheck(time: String, food: String, cals: String): Boolean {
        return !(TextUtils.isEmpty(time) || TextUtils.isEmpty(food) || TextUtils.isEmpty(cals))
    }
}