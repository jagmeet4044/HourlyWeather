package com.jagmeet.android.hourlyweather.datasource.network.citylookup.response

data class CityLookupResponseItem(
    val country: String,
    val lat: Double,
    val local_names: LocalNames,
    val lon: Double,
    val name: String,
    val state: String
)

