package com.kathleenwang.animalcrossingfish

import android.util.Log
import org.json.JSONObject

class Fish(val name: String, val price: Int, var availability: Availability, val imageSrc: String) {

    override fun toString(): String {
        return "$name, price=$price, availability:$availability, imageSrc=$imageSrc"
    }
}

class Availability(val months:String,val location:String,val rarity:String,var available: Boolean = false) {
    init {
        val currentMonth = 12
        val monthsSplit = months.split("-")
        val first = monthsSplit.first().toInt()
        val last = monthsSplit.last().toInt()
        if (last < first) {
            if (currentMonth >= first || currentMonth <= last) available = true}
        else { if (currentMonth in first..last) available = true }
    }

    override fun toString(): String {
        return "months=$months, location=$location, rarity=$rarity, available=$available"
    }

}
