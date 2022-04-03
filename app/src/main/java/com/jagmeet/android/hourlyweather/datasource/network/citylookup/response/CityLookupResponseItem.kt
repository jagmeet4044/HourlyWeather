package com.jagmeet.android.hourlyweather.datasource.network.citylookup.response

import com.jagmeet.android.hourlyweather.model.CityDetail

data class CityLookupResponseItem(
    val country: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val state: String
)

fun CityLookupResponseItem.toCityDetail(): CityDetail {
    return CityDetail(
        country = this.country,
        name = this.name,
        lat = this.lat,
        lon = this.lon,
        state = this.state
    )
}
