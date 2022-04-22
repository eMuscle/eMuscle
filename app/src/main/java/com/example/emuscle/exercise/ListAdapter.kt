package com.example.emuscle.exercise

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.emuscle.R
import com.example.emuscle.database.Exercise
import kotlinx.android.synthetic.main.exercise_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    //Luodaan lista harjoituksista
    private var exerciseList = emptyList<Exercise>()

    //Tarpeellinen class onCreateViewHolder funktiolle
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    //Funktio joka luo uuden rivin RecyclerViewiin käyttäen tiedostoa exercise_row.xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.exercise_row, parent, false))
    }

    //Funktio joka välittää painetun harjoituksen tiedot ExerciseUpdatelle
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = exerciseList[position]
        holder.itemView.TVExercise.text = currentItem.exercise
        holder.itemView.TVSets.text = currentItem.sets
        holder.itemView.TVReps.text = currentItem.reps
        holder.itemView.TVWeight.text = currentItem.weight

        holder.itemView.rowLayout.setOnClickListener {
            val activity = holder.itemView.context as Activity
            val intent = Intent(activity, ExerciseUpdate::class.java)
            intent.putExtra("id", currentItem.id.toString())
            intent.putExtra("day", currentItem.day)
            intent.putExtra("exercise", currentItem.exercise)
            intent.putExtra("sets", currentItem.sets)
            intent.putExtra("reps", currentItem.reps)
            intent.putExtra("weight", currentItem.weight)
            activity.startActivity(intent)
        }
    }

    //Funktio joka kertoo harjoitusten määrän. Adapterin kirjastossa
    override fun getItemCount(): Int {
        return exerciseList.size
    }

    //Funktio asettaa listan RecyclerViewiin. Adapterin kirjastossa
    fun setData(exercise: List<Exercise>) {
        this.exerciseList = exercise
        notifyDataSetChanged()
    }

}