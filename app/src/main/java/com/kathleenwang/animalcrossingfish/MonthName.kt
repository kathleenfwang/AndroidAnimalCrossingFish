package com.kathleenwang.animalcrossingfish

import java.text.SimpleDateFormat
import java.util.*

class MonthName {
    var month = ""
    init {
        val cal = Calendar.getInstance()
        month = SimpleDateFormat("MMM").format(cal.getTime())
    }
}