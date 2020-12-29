package com.kathleenwang.animalcrossingfish

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        flickrTask().execute()
    }
    inner class flickrTask(): AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String? {
            var response: String?
            val url = "https://acnhapi.com/v1/fish"
            response = try {

                URL(url).readText(
                        Charsets.UTF_8
                )
            } catch (e: Exception) {
                null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                /* Extracting JSON returns from the API */
                val jsonObj = JSONObject(result)
                Log.d("Post:", jsonObj.toString())

            } catch (e: Exception) {
                Log.d("Post:", "error")
            }
        }
    }
}