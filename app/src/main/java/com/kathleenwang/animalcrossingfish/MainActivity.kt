package com.kathleenwang.animalcrossingfish

import android.content.Context
import android.location.Location
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.Debug.getLocation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject
import org.w3c.dom.Text
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlinx.android.synthetic.main.activity_main.*
private var fishes = mutableListOf<Fish>()
private var LOCATION = "River"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = FishAdapter(this, fishes)
        // button listeners
        riverButton.setOnClickListener {
            LOCATION ="River"
            val adapter = FishAdapter(this, fishes)
        }
        seaButton.setOnClickListener {
            LOCATION = "Sea"
            fishTask(this,rvList,adapter).execute()
        }
        otherButton.setOnClickListener {
            LOCATION = "other"
            val adapter = FishAdapter(this, fishes)
        }
        allButton.setOnClickListener {
            LOCATION = "all"
            val adapter = FishAdapter(this, fishes)
        }


        fishTask(this,rvList,adapter).execute()
    }
    inner class fishTask(context: Context, listView: RecyclerView, adapter: FishAdapter): AsyncTask<String, Void, String>() {
        var propContext = context
        var propListView = listView
        var propAdapter = adapter
        override fun doInBackground(vararg params: String?): String? {
            var response: String?
            val url = "https://acnhapi.com/v1/fish"
            response = try {
                URL(url).readText(Charsets.UTF_8)
            } catch (e: Exception) {
                null
            }
            Log.d("background", "background complete")
            return response
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            rvList.adapter = propAdapter
            rvList.layoutManager = LinearLayoutManager(propContext)
            val fishItems = ArrayList<Fish>()
            val cal = Calendar.getInstance()
            val month = SimpleDateFormat("MMM").format(cal.getTime())
            try {
                /* Extracting JSON returns from the API */
                val jsonObj = JSONObject(result)
                val jsonKeys = jsonObj.keys()
                for (i in jsonKeys) {
                    val item = jsonObj.getJSONObject(i)
                    val availability = item.getJSONObject("availability")
                    val months = availability["month-northern"].toString()
                    val newFish = Fish(item["file-name"].toString(), item["price"].toString().toInt(), Availability(months, availability["location"].toString(), availability["rarity"].toString() ), item["image_uri"].toString())
                    fishItems.add(newFish)
                }

                val filteredFishItems = fishItems.filter{ it.availability.available}

                fishes.addAll(filteredFishItems)
                mainText.text = "Total fish available for ${month}: ${filteredFishItems.size} / ${fishItems.size}"
                propAdapter.notifyDataSetChanged()


            } catch (e: Exception) {
                Log.d("Post:", "$e")
            }
        }


    }
}