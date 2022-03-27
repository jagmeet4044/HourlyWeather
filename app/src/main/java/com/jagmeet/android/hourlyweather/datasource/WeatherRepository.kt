package com.jagmeet.android.hourlyweather.datasource

import com.jagmeet.android.hourlyweather.datasource.network.citylookup.CityLookupService
import com.jagmeet.android.hourlyweather.datasource.network.citylookup.response.CityLookupResponse
import com.jagmeet.android.hourlyweather.datasource.network.citylookup.response.CityLookupResponseItem
import com.jagmeet.android.hourlyweather.datasource.network.toCityDetail
import com.jagmeet.android.hourlyweather.datasource.network.weather.WeatherService
import com.jagmeet.android.hourlyweather.datasource.network.weather.response.toWeatherData
import com.jagmeet.android.hourlyweather.model.CityDetail
import com.jagmeet.android.hourlyweather.model.HourlyWeatherData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherService: WeatherService,
    private val cityLookupService: CityLookupService
) {
    suspend fun getHourlyForecast(lat: Double, lon: Double): HourlyWeatherData =
        withContext(Dispatchers.Default) {
            var weatherResponse =
                weatherService.getWeatherForecast(lat,lon)
            weatherResponse.toWeatherData()
        }

    suspend fun getCityDetails(city: String): CityDetail =
        withContext(Dispatchers.Default) {
            var cityLookupResponse =
                cityLookupService.getCityDetail(city)
            cityLookupResponse[0].toCityDetail()
        }
}



