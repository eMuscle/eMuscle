package com.example.emuscle.diet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.emuscle.R
import com.example.emuscle.database.DietViewModel
import com.example.emuscle.databinding.ActivityCalendarDietDayBinding

class CalendarDietDay : AppCompatActivity() {
    private lateinit var mDietViewModel: DietViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_diet_day)

        val button = findViewById<Button>(R.id.buttonForFood)

        //Vastaanottaa stringinä päiväkohtaisen id:n arvon
        val id = intent.getStringExtra("id").toString()

        //Luodaan muuttuja adapter joka on tyyppiä ListAdapter
        val adapter = DietListAdapter()
        val dietLayout = findViewById<RecyclerView>(R.id.diet_recycler_view)
        //RecyclerViewille määritetään adapter
        dietLayout.adapter = adapter
        dietLayout.layoutManager = LinearLayoutManager(this)

        //Luodaan ViewModel DietViewModel luokasta
        mDietViewModel = ViewModelProvider(this)[DietViewModel::class.java]
        //Tutkitaan tietokannan Diet taulun muutoksia. Jos tulee muutos, RecyclerView päivittyy
        mDietViewModel.getDietByDay(id).observe(this) { diet ->
            adapter.setData(diet)
        }

        //Avaa DietPopUp näkymän
        button.setOnClickListener {
            val intent = Intent(this, DietPopUp::class.java)
            intent.putExtra("day", id)
            startActivity(intent)
        }
    }
}