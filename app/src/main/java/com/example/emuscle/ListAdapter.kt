package com.example.emuscle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.emuscle.database.Exercise
import kotlinx.android.synthetic.main.exercise_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var exerciseList = emptyList<Exercise>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.exercise_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = exerciseList[position]
        holder.itemView.TVExercise.text = currentItem.exercise
        holder.itemView.TVSets.text = currentItem.sets
        holder.itemView.TVReps.text = currentItem.reps
        holder.itemView.TVWeight.text = currentItem.weight
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    fun setData(exercise: List<Exercise>) {
        this.exerciseList = exercise
        notifyDataSetChanged()
    }

}