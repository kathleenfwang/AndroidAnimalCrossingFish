package com.kathleenwang.animalcrossingfish

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL


class MainActivity : AppCompatActivity() {
    var mainText:TextView?  = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainText = findViewById<TextView>(R.id.mainText)
        fishTask().execute()
    }
    inner class fishTask(): AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String? {
            var response: String?
            val url = "https://acnhapi.com/v1/fish"
            response = try {
                URL(url).readText(Charsets.UTF_8)
            } catch (e: Exception) {
                null
            }
            return response
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            try {
                /* Extracting JSON returns from the API */
                val jsonObj = JSONObject(result)
                val jsonKeys = jsonObj.keys()
                val fishItems = mutableListOf<Fish>()
                for (i in jsonKeys) {
                    val item = jsonObj.getJSONObject(i)
                    val availability = item.getJSONObject("availability")
                    val months = availability["month-northern"].toString()
                    val newFish = Fish(item["file-name"].toString(), item["price"].toString().toInt(), Availability(months, availability["location"].toString(), availability["rarity"].toString() ), item["image_uri"].toString())
                    fishItems.add(newFish)
                }
                mainText!!.text = fishItems.toString()
            } catch (e: Exception) {
                Log.d("Post:", "error")
            }
        }
    }
}