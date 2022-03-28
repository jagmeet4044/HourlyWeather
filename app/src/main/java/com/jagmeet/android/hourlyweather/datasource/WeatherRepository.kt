package com.jagmeet.android.hourlyweather.datasource

import com.jagmeet.android.hourlyweather.ResultData
import com.jagmeet.android.hourlyweather.datasource.network.citylookup.CityLookupService
import com.jagmeet.android.hourlyweather.datasource.network.citylookup.response.toCityDetail
import com.jagmeet.android.hourlyweather.datasource.network.safeApiCall

import com.jagmeet.android.hourlyweather.datasource.network.weather.WeatherService
import com.jagmeet.android.hourlyweather.datasource.network.weather.response.toWeatherData
import com.jagmeet.android.hourlyweather.model.CityDetail
import com.jagmeet.android.hourlyweather.model.HourlyWeatherData
import com.jagmeet.android.hourlyweather.ui.weather.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherService: WeatherService,
    private val cityLookupService: CityLookupService
) {
    suspend fun getHourlyForecast(lat: Double, lon: Double): ResultData<HourlyWeatherData> {
        return when (val response =
            safeApiCall(Dispatchers.IO) { weatherService.getWeatherForecast(lat, lon) }) {
            is ResultWrapper.Success -> {
                ResultData.Success(response.value.toWeatherData())
            }
            is ResultWrapper.NetworkError ->
                ResultData.Error("network issue")
            is ResultWrapper.GenericError ->
                ResultData.Error(response.error)
        }
    }

    suspend fun getCityDetails(city: String): ResultData<CityDetail> {
        return when (val response =
            safeApiCall(Dispatchers.IO) { cityLookupService.getCityDetail(city) }) {
            is ResultWrapper.Success -> {
                if (response.value.isEmpty())
                    ResultData.Error("invalid city name")
                else
                    ResultData.Success(response.value[0].toCityDetail())
            }
            is ResultWrapper.NetworkError ->
                ResultData.Error("network issue")
            is ResultWrapper.GenericError ->
                ResultData.Error(response.error)
        }
    }

}



