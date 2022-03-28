package com.jagmeet.android.hourlyweather.ui.weather.lookup

import com.jagmeet.android.hourlyweather.model.CityDetail

data class CityLookUpState(
    val isLoading: Boolean = false,
    val isLookUpSuccess: Boolean = false,
    val errorMessages: List<Message>? = emptyList(),
    val cityDetail: CityDetail?
)

data class Message(val id: Long, val message: String)