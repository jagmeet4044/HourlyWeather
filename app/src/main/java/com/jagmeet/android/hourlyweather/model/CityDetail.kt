package com.jagmeet.android.hourlyweather.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CityDetail(
    val name: String,
    val state: String,
    val country: String,
    val lat: Double,
    val lon: Double
) : Parcelable
