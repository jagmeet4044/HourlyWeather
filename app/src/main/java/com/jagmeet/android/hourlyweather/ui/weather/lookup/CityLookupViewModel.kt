package com.jagmeet.android.hourlyweather.ui.weather.lookup

import androidx.lifecycle.*
import com.jagmeet.android.hourlyweather.ResultData
import com.jagmeet.android.hourlyweather.datasource.network.citylookup.CityLookUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class CityLookupViewModel @Inject constructor(
    private val cityLookUpRepository: CityLookUpRepository
) : ViewModel() {

    private var _cityLookUpState =
        MutableLiveData(CityLookUpState(cityDetail = null))
    var cityLookUpState: LiveData<CityLookUpState> = _cityLookUpState


    fun getCityDetail(cityName: String) {
        if (cityName.isBlank()) {
            val messages = _cityLookUpState.value?.errorMessages?.toMutableList()
            messages?.add(Message(Random(10).nextLong(), "Enter city name"))
            _cityLookUpState.value =
                (_cityLookUpState.value?.copy(
                    isLookUpSuccess = false,
                    errorMessages = messages
                ))
            return
        }
        Timber.d(" Timber getCityDetail viewmodel")
        viewModelScope.launch {
            var cityDetailResult = cityLookUpRepository.getCityDetails(cityName)
            when (cityDetailResult) {
                is ResultData.Success -> {
                    val cityDetail = cityDetailResult.value
                    Timber.d(" Timber getCityDetail viewmodel ${cityDetail.country}")
                    _cityLookUpState.value =
                        (_cityLookUpState.value?.copy(
                            isLookUpSuccess = true,
                            cityDetail = cityDetail
                        ))
                }
                is ResultData.Error -> {
                    Timber.d(" Timber getCityDetail viewmodel ResultData.Error")
                    val messages = _cityLookUpState.value?.errorMessages?.toMutableList()
                    messages?.add(Message(Random(10).nextLong(), "invalid city name"))
                    _cityLookUpState.value =
                        (_cityLookUpState.value?.copy(
                            isLookUpSuccess = false,
                            errorMessages = messages
                        ))
                }
            }

        }

    }


    fun messageShown(messageId: Long) {
        val messages = _cityLookUpState.value?.errorMessages?.filterNot { it.id == messageId }
        _cityLookUpState.value = _cityLookUpState.value?.copy(errorMessages = messages)
    }

    fun navigateFromLookUp() {
        _cityLookUpState.value =
            (_cityLookUpState.value?.copy(isLookUpSuccess = false, errorMessages = emptyList()))
    }
}