package com.jagmeet.android.hourlyweather.datasource.network.citylookup

import com.jagmeet.android.hourlyweather.ResultData
import com.jagmeet.android.hourlyweather.datasource.network.citylookup.CityLookupService
import com.jagmeet.android.hourlyweather.datasource.network.citylookup.response.toCityDetail
import com.jagmeet.android.hourlyweather.datasource.network.safeApiCall

import com.jagmeet.android.hourlyweather.datasource.network.weather.response.toWeatherData
import com.jagmeet.android.hourlyweather.model.CityDetail
import com.jagmeet.android.hourlyweather.model.HourlyWeatherData
import com.jagmeet.android.hourlyweather.ui.weather.ResultWrapper
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CityLookUpRepositoryImpl @Inject constructor(
    private val cityLookupService: CityLookupService
) : CityLookUpRepository {
    override suspend fun getCityDetails(city: String): ResultData<CityDetail> {
        return when (val response =
            safeApiCall(Dispatchers.IO) { cityLookupService.getCityDetail(city) }) {
            is ResultWrapper.Success -> {
                if (response.value.isEmpty())
                    ResultData.Error("Invalid city name") //to be changed to refer from string.xml
                else
                    ResultData.Success(response.value[0].toCityDetail())
            }
            is ResultWrapper.NetworkError ->
                ResultData.Error("Network issue")
            is ResultWrapper.GenericError ->
                ResultData.Error(response.error)
        }
    }

}



