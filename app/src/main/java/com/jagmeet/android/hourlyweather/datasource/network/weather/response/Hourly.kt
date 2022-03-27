package com.jagmeet.android.hourlyweather.datasource.network.weather.response

data class Hourly(
    val clouds: Double,
    val dew_point: Double,
    val dt: Double,
    val feels_like: Double,
    val humidity: Double,
    val pop: Double,
    val pressure: Int,
    val temp: Double,
    val uvi: Double,
    val visibility: Int,
    val weather: List<Weather>,
    val wind_deg: Int,
    val wind_gust: Double,
    val wind_speed: Double
)