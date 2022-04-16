package com.example.emuscle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        //RecyclerView setup
        val adapter = DietListAdapter()
        val dietLayout = findViewById<RecyclerView>(R.id.diet_recycler_view)
        dietLayout.adapter = adapter
        dietLayout.layoutManager = LinearLayoutManager(this)

        mDietViewModel = ViewModelProvider(this)[DietViewModel::class.java]

        mDietViewModel.getDietByDay(id).observe(this) { diet ->
            adapter.setData(diet)
        }

        binding.buttonForFood.setOnClickListener {
            val intent = Intent(this, DietPopUp::class.java)
            intent.putExtra("day", id)
            startActivity(intent)
        }
    }
}