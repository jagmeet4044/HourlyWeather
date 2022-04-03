package com.jagmeet.android.hourlyweather.datasource.network.weather

import com.jagmeet.android.hourlyweather.ResultData
import com.jagmeet.android.hourlyweather.model.HourlyWeatherData

interface WeatherRepository {
    suspend fun getHourlyForecast(lat: Double, lon: Double): ResultData<HourlyWeatherData>
}