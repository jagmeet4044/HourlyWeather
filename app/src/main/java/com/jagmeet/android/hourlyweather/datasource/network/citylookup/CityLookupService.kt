package com.jagmeet.android.hourlyweather.datasource.network.citylookup

import com.jagmeet.android.hourlyweather.Constants
import com.jagmeet.android.hourlyweather.datasource.network.citylookup.response.CityLookupResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CityLookupService {
    @GET("direct")
    suspend fun getCityDetail(
        @Query("q") city: String,
    ): CityLookupResponse

}