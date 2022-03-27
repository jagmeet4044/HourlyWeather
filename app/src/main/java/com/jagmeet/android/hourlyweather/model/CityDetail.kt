package com.jagmeet.android.hourlyweather.model

import com.jagmeet.android.hourlyweather.datasource.network.citylookup.response.LocalNames

data class CityDetail(
    val country: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val state: String
)