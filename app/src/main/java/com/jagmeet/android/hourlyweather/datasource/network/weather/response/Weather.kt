package com.jagmeet.android.hourlyweather.datasource.network.weather.response

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)