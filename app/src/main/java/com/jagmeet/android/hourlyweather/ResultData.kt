package com.jagmeet.android.hourlyweather

sealed class ResultData<out T> {
    data class Success<out T>(val value: T) : ResultData<T>()
    data class Error(val error: String? = null) :
        ResultData<Nothing>()
}