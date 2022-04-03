package com.jagmeet.android.hourlyweather.datasource.network.weather

import com.jagmeet.android.hourlyweather.datasource.network.weather.response.HourlyWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("onecall")
    suspend fun getWeatherForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
    ): HourlyWeatherResponse

}