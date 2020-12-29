package com.kathleenwang.animalcrossingfish

import android.os.AsyncTask
import java.lang.Exception

class GetFishJsonData (private val listener: OnDataAvailable) : AsyncTask<String, Void, ArrayList<Fish>>(){
    private val TAG = "GetFishJsonData"
    interface OnDataAvailable {
        fun onDataAvailable(data: List<Fish>)
        fun onError(exception: Exception)
    }

    override fun doInBackground(vararg params: String?): ArrayList<Fish> {
        TODO("Not yet implemented")
    }
}