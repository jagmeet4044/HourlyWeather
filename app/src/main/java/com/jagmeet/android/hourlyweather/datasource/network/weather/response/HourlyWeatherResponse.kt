package com.jagmeet.android.hourlyweather.datasource.network.weather.response

import com.jagmeet.android.hourlyweather.Utils.Companion.getTime
import com.jagmeet.android.hourlyweather.model.HourlyData
import com.jagmeet.android.hourlyweather.model.HourlyWeatherData
import com.jagmeet.android.hourlyweather.model.WeatherData

data class HourlyWeatherResponse(
    val hourly: List<Hourly>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int
)


fun HourlyWeatherResponse.toWeatherData(): HourlyWeatherData {
    return HourlyWeatherData(
        hourly = hourly.toHourlyData(),
        lat = lat,
        lon = lon,
        timezone = timezone,
        timezone_offset = timezone_offset
    );
}

private fun List<Hourly>.toHourlyData(): List<HourlyData> {
    var hourlyDataList = ArrayList<HourlyData>()
    for (hourly in this) {
        hourlyDataList.add(
            HourlyData(
                dt = getTime(hourly.dt),
                clouds = hourly.clouds,
                weather = hourly.weather.toWeatherData(),
                feels_like = hourly.feels_like,
                temp = hourly.temp
            )
        )
    }
    return hourlyDataList
}

private fun List<Weather>.toWeatherData(): List<WeatherData> {
    var weatherDataList = ArrayList<WeatherData>()
    for (weather in this) {
        weatherDataList.add(
            WeatherData(
                description = weather.description,
                icon = weather.icon,
                id = weather.id,
                main = weather.main
            )
        )
    }
    return weatherDataList
}
