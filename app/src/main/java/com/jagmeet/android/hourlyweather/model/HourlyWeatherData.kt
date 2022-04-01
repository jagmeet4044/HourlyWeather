package com.jagmeet.android.hourlyweather.model

data class HourlyWeatherData(
    val hourly: List<HourlyData>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int
)

data class HourlyData(
    val clouds: Double = 0.0,
    val dew_point: Double = 0.0,
    val dt: String = "",
    val feels_like: Double,
    val humidity: Int = 0,
    val pop: Int = 0,
    val pressure: Int = 0,
    val temp: Double,
    val uvi: Int = 0,
    val visibility: Int = 0,
    val weather: List<WeatherData>,
    val wind_deg: Int = 0,
    val wind_gust: Double = 0.0,
    val wind_speed: Double = 0.0
)

data class WeatherData(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)