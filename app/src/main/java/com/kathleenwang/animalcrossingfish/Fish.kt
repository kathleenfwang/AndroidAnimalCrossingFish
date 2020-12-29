package com.kathleenwang.animalcrossingfish

import android.util.Log
import org.json.JSONObject

class Fish(val name: String, val price: Int, var availability: Availability, val imageSrc: String) {

    override fun toString(): String {
        return "$name, price=$price, availability:$availability, imageSrc=$imageSrc"
    }
}

class Availability(val months:String,val location:String,val rarity:String,) {
    var available = false

    init {
        available = false
        if (months.length <= 1) {
            available = true
        }
        else {
        val currentMonth = 12
        var monthsSplit = months.split("-")
        val first = monthsSplit.first().toInt()
        val last = monthsSplit.last().toInt()
            available = if (last < first) {
                currentMonth >= first || currentMonth <= last
            } else {
                currentMonth in first..last
            }

    }
        Log.d("month:", available.toString())
}

    override fun toString(): String {
        return "months=$months, location=$location, rarity=$rarity, available=$available"
    }

}




