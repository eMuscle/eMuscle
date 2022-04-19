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

class DietListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var dietList = emptyList<Diet>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.MyViewHolder {
        return ListAdapter.MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.diet_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListAdapter.MyViewHolder, position: Int) {
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

    override fun getItemCount(): Int {
        return dietList.size
    }

    fun setData(diet: List<Diet>) {
        this.dietList = diet
        notifyDataSetChanged()
    }

}