package com.jagmeet.android.hourlyweather.datasource.network.weather

import com.jagmeet.android.hourlyweather.Constants
import com.jagmeet.android.hourlyweather.datasource.network.weather.response.HourlyWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("onecall")
    suspend fun getWeatherForecast(
        //lat=-41.211128&lon=174.908081
        // @Query("q") country: String = "london",
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String = Constants.API_KEY
    ): HourlyWeatherResponse

}