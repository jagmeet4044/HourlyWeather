package com.jagmeet.android.hourlyweather.datasource.network.citylookup

import com.jagmeet.android.hourlyweather.ResultData
import com.jagmeet.android.hourlyweather.model.CityDetail

interface CityLookUpRepository {
    suspend fun getCityDetails(city: String): ResultData<CityDetail>
}