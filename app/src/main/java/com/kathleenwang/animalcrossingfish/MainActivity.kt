package com.kathleenwang.animalcrossingfish

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import org.w3c.dom.Text
import java.net.URL


class MainActivity : AppCompatActivity() {
    var xmlListView: ListView? = null
    var mainText:TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        xmlListView = findViewById(R.id.xmlListView)
        mainText = findViewById(R.id.mainText)
        fishTask(this,xmlListView!!).execute()
    }
    inner class fishTask(context: Context, listView: ListView): AsyncTask<String, Void, String>() {
        var propContext = context
        var propListView = listView
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
            val fishItems = ArrayList<Fish>()
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

                Log.d("Post:","${fishItems}")
                var arrayAdapter = ArrayAdapter<Fish>(propContext,android.R.layout.simple_list_item_1, fishItems)

//                mainText!!.text = "Total fish: ${fishItems.size}"
                propListView.adapter = arrayAdapter
            } catch (e: Exception) {
                Log.d("Post:", "error")
            }

        }
    }
}