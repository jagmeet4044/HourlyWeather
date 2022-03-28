package com.jagmeet.android.hourlyweather.ui.weather

import android.util.Log
import androidx.lifecycle.*
import com.jagmeet.android.hourlyweather.datasource.WeatherRepository
import com.jagmeet.android.hourlyweather.model.HourlyData
import com.jagmeet.android.hourlyweather.model.HourlyWeatherData
import com.jagmeet.android.hourlyweather.model.WeatherData
import com.jagmeet.android.hourlyweather.ui.weather.lookup.CityLookUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HourlyWeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _weatherData = MutableLiveData<HourlyWeatherData>()
    var hourlyWeatherData: LiveData<HourlyWeatherData> = _weatherData

    private var _selectedWeatherData = MutableLiveData<HourlyData>()
    var selectedWeatherData: LiveData<HourlyData> = _selectedWeatherData

    private var _cityLookUpState =
        MutableLiveData<CityLookUpState>(CityLookUpState(cityDetail = null))
    var cityLookUpState: LiveData<CityLookUpState> = _cityLookUpState

    init {
//        Log.d("jagmeetnir", "init viewmodel")
//        savedStateHandle.get<String>("city")?.let { city ->
//            getHourlyWeather(city)
//        }
//        savedStateHandle.get<String>("test")?.let { test ->
//            Log.d("jagmeetnir", "init viewmodel test")
//        }
    }

    fun getHourlyWeather() {
        Timber.d(" Timber getHourlyWeather viewmodel")
        viewModelScope.launch {
            _cityLookUpState.value.let {
                var weatherData = weatherRepository.getHourlyForecast(
                    _cityLookUpState.value?.cityDetail?.lat!!,
                    _cityLookUpState.value?.cityDetail?.lat!!
                )
                _weatherData.postValue(weatherData)
            }

        }
    }

    fun getCityDetail(cityName: String) {
        Timber.d(" Timber getCityDetail viewmodel")
        viewModelScope.launch {
            var cityDetail = weatherRepository.getCityDetails(cityName)
            Timber.d(" Timber getHourlyWeather viewmodel ${cityDetail.country}")
            _cityLookUpState.value =
                (cityLookUpState.value?.copy(isLookUpSuccess = true, cityDetail = cityDetail))
        }
    }

    fun messageShown(messageId: Long) {
        val messages = _cityLookUpState.value?.userMessages?.filterNot { it.id == messageId }
        _cityLookUpState.value?.copy(userMessages = messages)
    }

    fun setSelectedData(item: HourlyData) {
        _selectedWeatherData.value = item
    }

    fun navigateFromLookUp() {
        _cityLookUpState.value =
            (cityLookUpState.value?.copy(isLookUpSuccess = false))
    }

}