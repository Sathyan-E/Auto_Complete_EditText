package com.example.autocompleteedittext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), OnPlaceClickListener {

    private lateinit var search:EditText
    private lateinit var button:Button
    private lateinit var result:TextView
    private lateinit var sBuilder:java.lang.StringBuilder
    private lateinit var list:ArrayList<AutocompletePrediction>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PlaceAutoSuggestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        search=findViewById(R.id.search_box)
        button=findViewById(R.id.search_buttton)
        result=findViewById(R.id.search_results)
        recyclerView=findViewById(R.id.recycler_view)
        recyclerView.layoutManager=LinearLayoutManager(applicationContext)
        list=ArrayList()
        adapter= PlaceAutoSuggestAdapter(list,this)
        recyclerView.adapter=adapter

        val apiKey="AIzaSyD2BU6x8RqFCvHX4BnrIaI0f1ycabOcl2k"


       if (!Places.isInitialized()){
           Places.initialize(applicationContext,apiKey)
       }
        val placesClient=Places.createClient(this)

        button.setOnClickListener {
        }

        search.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
               // TODO("Not yet implemented")
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
               // TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //TODO("Not yet implemented")
                val token=AutocompleteSessionToken.newInstance()
                val bound=RectangularBounds.newInstance(
                LatLng(0.0,0.0), LatLng(0.0,0.0)
                )
                val request=FindAutocompletePredictionsRequest.builder()
                                            .setLocationBias(bound)
                                            .setTypeFilter(TypeFilter.CITIES)
                                            .setSessionToken(token)
                                            .setQuery(search.text.toString()).build()
                placesClient.findAutocompletePredictions(request).addOnSuccessListener { findAutocompletePredictionsResponse ->
                sBuilder= StringBuilder()
                 list.clear()
                for(prediction:AutocompletePrediction in findAutocompletePredictionsResponse.autocompletePredictions){
                    list.add(prediction)
                    sBuilder.append(" ").append(prediction.getFullText(null)).toString()+"\n"
                    Toast.makeText(this@MainActivity,"Place ID is"+prediction.getFullText(null),Toast.LENGTH_SHORT).show()
                }
                    adapter.notifyDataSetChanged()
                result.text=sBuilder.toString()

                }.addOnFailureListener{
                Toast.makeText(this@MainActivity,"add failure listener",Toast.LENGTH_SHORT).show()
                }

        }

        })


    }

    override fun onItemClick(place: AutocompletePrediction, pos: Int) {
        //TODO("Not yet implemented")
        Toast.makeText(this,place.getFullText(null),Toast.LENGTH_SHORT).show()
    }


}