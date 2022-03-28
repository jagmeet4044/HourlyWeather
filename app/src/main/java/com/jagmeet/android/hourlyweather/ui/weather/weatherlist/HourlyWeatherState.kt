package com.jagmeet.android.hourlyweather.ui.weather.weatherlist

import com.jagmeet.android.hourlyweather.model.CityDetail
import com.jagmeet.android.hourlyweather.model.HourlyData
import com.jagmeet.android.hourlyweather.model.HourlyWeatherData

data class HourlyWeatherState(
    val isLoading: Boolean = false,
    val hourlyWeatherDataList: List<HourlyData> = emptyList(),
    val cityDetail: CityDetail? = null,
    val selectedWeatherData: HourlyData? = null,
    val errorMessages: List<Message>? = emptyList(),
)

data class Message(val id: Long, val message: String?)
