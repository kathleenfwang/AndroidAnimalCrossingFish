package com.kathleenwang.animalcrossingfish

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class Fish(var name: String, val price: Int, var availability: Availability, val imageSrc: String) {
    init {
            var newName = mutableListOf<String>()
            var newList = name.split("_") // ["gold", "fish"]

            for (i in newList) {
                var word = i[0].toUpperCase() + i.substring(startIndex = 1)
                newName.add(word)
            }
        val finalName = java.lang.String.join(" ", newName)
        Log.d("Fish: ", finalName)
            name = finalName
    }
    override fun toString(): String {
        return "$name, price=$price, availability:$availability, imageSrc=$imageSrc"
    }
}

class Availability(val months:String,val location:String,val rarity:String,) {
    var available = false

    init {
        available = if (months.length <= 1) { true }
        else {
            val month = Calendar.getInstance().get(Calendar.MONTH)
            var monthsSplit = months.split("-")
            val first = monthsSplit.first().toInt()
            val last = monthsSplit.last().toInt()
            if (last < first) {
                month >= first || month <= last
            } else {
                month in first..last
            }
        }
        Log.d("month:", available.toString())
}
    override fun toString(): String {
        return "months=$months, location=$location, rarity=$rarity, available=$available"
    }

}




