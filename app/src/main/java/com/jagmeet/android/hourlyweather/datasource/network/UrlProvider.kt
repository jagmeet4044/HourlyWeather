package com.jagmeet.android.hourlyweather.datasource.network

import android.util.Log
import com.jagmeet.android.hourlyweather.Constants

class UrlProvider {
    init {
        Log.d("jagmeet", "init Url provider")
    }

    fun getWeatherUrl(): String {
        return Constants.BASE_URL
    }

    fun getGeocodingUrl(): String {
        return Constants.GEOCODING_URL
    }
}