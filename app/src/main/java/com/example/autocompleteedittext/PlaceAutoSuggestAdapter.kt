package com.example.autocompleteedittext

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.libraries.places.api.model.AutocompletePrediction

class PlaceAutoSuggestAdapter(private val list:ArrayList<AutocompletePrediction>,var clickListener:OnPlaceClickListener): RecyclerView.Adapter<PlaceAutoSuggestAdapter.PlaceViewHolder>() {



    class PlaceViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){
        val placeTextView:TextView=itemview.findViewById(R.id.place_name)

        fun initialize(item:AutocompletePrediction,listener:OnPlaceClickListener){
            placeTextView.text=item.getPrimaryText(null)

            itemView.setOnClickListener{
                listener.onItemClick(item,adapterPosition)
            }
        }


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
        //holder.placeTextView.text=list[position].getPrimaryText(null)
        holder.initialize(list.get(position),clickListener)
    }
}
interface OnPlaceClickListener{
    fun onItemClick(place:AutocompletePrediction,pos:Int)

}