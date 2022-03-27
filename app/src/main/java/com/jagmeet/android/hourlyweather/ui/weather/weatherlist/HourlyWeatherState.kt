package com.jagmeet.android.hourlyweather.ui.weather.weatherlist

import com.jagmeet.android.hourlyweather.model.HourlyWeatherData

data class HourlyWeatherState(
    val isLoading: Boolean = false,
    val isLookUpSuccess: Boolean = false,
    val hourlyWeatherDataList: List<HourlyWeatherData>? = emptyList(),
    val userMessages: List<Message>? = emptyList(),
    val onSelect: () -> Unit
)

data class Message(val id: Long, val message: String)
