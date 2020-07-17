package com.example.autocompleteedittext

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlaceAutoSuggestAdapter(private val list:ArrayList<String>): RecyclerView.Adapter<PlaceAutoSuggestAdapter.PlaceViewHolder>() {


    class PlaceViewHolder(view: View):RecyclerView.ViewHolder(view){
        val placeTextView:TextView=view.findViewById(R.id.place_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
       // TODO("Not yet implemented")
        val view=LayoutInflater.from(parent.context).inflate(R.layout.place_item_layout,parent,false)
        return PlaceViewHolder(view)
    }

    override fun getItemCount(): Int {
       // TODO("Not yet implemented")
        return list.size
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
       // TODO("Not yet implemented")
        holder.placeTextView.text=list[position]
    }
}