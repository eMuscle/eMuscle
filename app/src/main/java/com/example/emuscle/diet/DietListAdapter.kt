package com.example.emuscle.diet

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.emuscle.R
import com.example.emuscle.database.Diet
import com.example.emuscle.exercise.ListAdapter
import kotlinx.android.synthetic.main.diet_row.view.*

class DietListAdapter: RecyclerView.Adapter<DietListAdapter.MyViewHolder>() {

    //Luodaan lista päivän aterioista
    private var dietList = emptyList<Diet>()

    //Tarpeellinen class onCreateViewHolder funktiolle
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    //Funktio joka luo uuden rivin RecyclerViewiin käyttäen tiedostoa diet_row.xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.diet_row, parent, false))
    }

    //Funktio joka välittää painetun harjoituksen tiedot DietPopUpille
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dietList[position]
        holder.itemView.TVTime.text = currentItem.time
        holder.itemView.TVFood.text = currentItem.food
        holder.itemView.TVCalories.text = currentItem.calories

        holder.itemView.dietRowLayout.setOnClickListener {
            val activity = holder.itemView.context as Activity
            val intent = Intent(activity, DietPopUp::class.java)
            intent.putExtra("id", currentItem.id.toString())
            intent.putExtra("tempDay", currentItem.day)
            intent.putExtra("time", currentItem.time)
            intent.putExtra("food", currentItem.food)
            intent.putExtra("calories", currentItem.calories)
            activity.startActivity(intent)
        }
    }

    //Funktio joka kertoo aterioiden määrän. Adapterin kirjastossa
    override fun getItemCount(): Int {
        return dietList.size
    }

    //Funktio asettaa listan RecyclerViewiin. Adapterin kirjastossa
    fun setData(diet: List<Diet>) {
        this.dietList = diet
        notifyDataSetChanged()
    }

}