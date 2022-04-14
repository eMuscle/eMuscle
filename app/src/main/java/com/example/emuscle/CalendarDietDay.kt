package com.example.emuscle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.emuscle.database.Diet
import com.example.emuscle.database.DietViewModel
import com.example.emuscle.databinding.ActivityCalendarDietDayBinding

class CalendarDietDay : AppCompatActivity() {
    private lateinit var binding: ActivityCalendarDietDayBinding
    private lateinit var mDietViewModel: DietViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarDietDayBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val id = intent.getStringExtra("id").toString()

        val bfInput = binding.BreakfastInput
        val bfCal = binding.BreakfastCalories
        val lInput = binding.LunchInput
        val lCal = binding.LunchCalories
        val dinInput = binding.DinnerInput
        val dinCal = binding.DinnerCalories
        val supInput = binding.SupperInput
        val supCal = binding.SupperCalories
        val snackInput = binding.SnackInput
        val snackCal = binding.SnackCalories
        var tempDay = "null"

        mDietViewModel = ViewModelProvider(this)[DietViewModel::class.java]

        val diet = mDietViewModel.getDietByDay(id)
            bfInput.append(diet.breakFast)
            bfCal.append(diet.breakFastCal)
            lInput.append(diet.lunch)
            lCal.append(diet.lunchCal)
            dinInput.append(diet.dinner)
            dinCal.append(diet.dinnerCal)
            supInput.append(diet.supper)
            supCal.append(diet.supperCal)
            snackInput.append(diet.snacks)
            snackCal.append(diet.snacksCal)
            tempDay = diet.day

        binding.buDietSave.setOnClickListener {
            val dietObject = Diet(0,id,
                bfInput.text.toString(),
                bfCal.text.toString(),
                lInput.text.toString(),
                lCal.text.toString(),
                dinInput.text.toString(),
                dinCal.text.toString(),
                supInput.text.toString(),
                supCal.text.toString(),
                snackInput.text.toString(),
                snackCal.text.toString()
            )
            if(tempDay == "null") {
                mDietViewModel.addDiet(dietObject)
                Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()
            } else {
                mDietViewModel.updateDiet(dietObject)
                Toast.makeText(this, "Updated!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    }