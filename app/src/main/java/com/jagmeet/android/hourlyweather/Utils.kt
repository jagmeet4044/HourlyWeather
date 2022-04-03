package com.jagmeet.android.hourlyweather

import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object {
        fun getTime(s: Double): String {
            try {
                val sdf = SimpleDateFormat("h:mm a")
                val netDate = Date(s.toLong() * 1000)
                return sdf.format(netDate)
            } catch (e: Exception) {
                return e.toString()
            }
        }
    }
}