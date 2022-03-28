package com.jagmeet.android.hourlyweather.ui.weather


import androidx.lifecycle.*
import com.jagmeet.android.hourlyweather.ResultData
import com.jagmeet.android.hourlyweather.datasource.WeatherRepository
import com.jagmeet.android.hourlyweather.model.CityDetail
import com.jagmeet.android.hourlyweather.model.HourlyData
import com.jagmeet.android.hourlyweather.ui.weather.weatherlist.HourlyWeatherState
import com.jagmeet.android.hourlyweather.ui.weather.weatherlist.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HourlyWeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _hourlyWeatherState =
        MutableLiveData<HourlyWeatherState>(HourlyWeatherState())
    var hourlyWeatherState: LiveData<HourlyWeatherState> = _hourlyWeatherState


    fun getHourlyWeather(cityDetail: CityDetail) {

        _hourlyWeatherState.postValue(
            _hourlyWeatherState.value?.copy(
                cityDetail = cityDetail,
                isLoading = true
            )
        )

        Timber.d(" Timber getHourlyWeather viewmodel")
        viewModelScope.launch {
            var weatherResult = weatherRepository.getHourlyForecast(cityDetail.lat, cityDetail.lon)
            when (weatherResult) {
                is ResultData.Success -> {
                    _hourlyWeatherState.postValue(
                        _hourlyWeatherState.value?.copy(
                            hourlyWeatherDataList = weatherResult.value.hourly,
                            isLoading = false
                        )
                    )
                }
                is ResultData.Error -> {
                    val messages = _hourlyWeatherState.value?.errorMessages?.toMutableList()
                    messages?.add(Message(Random(10).nextLong(), weatherResult.error))
                    _hourlyWeatherState.postValue(
                        _hourlyWeatherState.value?.copy(
                            errorMessages = messages,
                            isLoading = false
                        )
                    )
                }
            }

        }

    }

    fun messageShown(messageId: Long) {
        val messages =
            _hourlyWeatherState.value?.errorMessages?.filterNot { it.id == messageId }
        _hourlyWeatherState.value = _hourlyWeatherState.value?.copy(errorMessages = messages)
    }

    fun setWeatherData(hourlyData: HourlyData) {
        _hourlyWeatherState.postValue(
            _hourlyWeatherState.value?.copy(
                selectedWeatherData = hourlyData,
                isLoading = true
            )
        )
    }

}