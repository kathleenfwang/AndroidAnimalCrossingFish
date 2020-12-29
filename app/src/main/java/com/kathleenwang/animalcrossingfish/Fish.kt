package com.kathleenwang.animalcrossingfish

import android.util.Log
import org.json.JSONObject

class Fish(val name: String, val price: Int, var availability: Availability, val imageSrc: String) {

    override fun toString(): String {
        return "Fish(name='$name', price=$price, availability='$availability', imageSrc='$imageSrc')"
    }
}

class Availability(val months:String,val location:String,val rarity:String) {
//    init {
//        val currentMonth = 12
//        val monthsSplit = months.split("-")
//        if (currentMonth >= monthsSplit.first().toInt() && currentMonth <= monthsSplit.last().toInt())
//            available = true
//        Log.d("fish:", "$available")
//    }

    override fun toString(): String {
        return "Availability(months='$months', location='$location', rarity='$rarity')"
    }

}
