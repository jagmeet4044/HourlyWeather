package com.jagmeet.android.hourlyweather.datasource.network

import com.jagmeet.android.hourlyweather.datasource.network.citylookup.response.CityLookupResponseItem
import com.jagmeet.android.hourlyweather.model.CityDetail


 fun CityLookupResponseItem.toCityDetail(): CityDetail {
    return CityDetail(
        country = this.country,
        name = this.name,
        lat = this.lat,
        lon = this.lon,
        state = this.state
    )
}
