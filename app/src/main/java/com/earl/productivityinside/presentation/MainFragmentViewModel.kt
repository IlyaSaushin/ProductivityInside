package com.earl.productivityinside.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earl.productivityinside.domain.Interactor
import com.earl.productivityinside.domain.mappers.LocationInfoDomainToUiMapper
import com.earl.productivityinside.domain.mappers.WeatherInfoDomainToPresentationMapper
import com.earl.productivityinside.presentation.models.CoordinatesForWeatherRequest
import com.earl.productivityinside.presentation.models.LocationInfoPresentation
import com.earl.productivityinside.presentation.models.WeatherInfoPresentation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(
    private val interactor: Interactor,
    private val locationInfoDomainToUiMapper: LocationInfoDomainToUiMapper<LocationInfoPresentation>,
    private val weatherInfoDomainToPresentationMapper: WeatherInfoDomainToPresentationMapper<WeatherInfoPresentation>
) : ViewModel() {

    private val locationInfo: MutableStateFlow<LocationInfoPresentation?> = MutableStateFlow(null)
    val _locationInfo = locationInfo.asStateFlow()
    private val weatherInfo: MutableStateFlow<WeatherInfoPresentation?> = MutableStateFlow(null)
    val _weatherInfo = weatherInfo.asStateFlow()

    fun fetchLocationByIp(lang: String, lastUpdateListener: UpdateTimeListener) {
        viewModelScope.launch(Dispatchers.IO) {
            val location = interactor.fetchLocationByIp(lang)?.mapToUi(locationInfoDomainToUiMapper)
            lastUpdateListener.saveLastUpdateTime(DateTimeGiver().fetchCurrentDateAndTime().format(DateTimeGiver().standardFormatter))
            withContext(Dispatchers.Main) { locationInfo.value = location }
        }
    }

    fun fetchWeatherFromServerOne(coordinates: CoordinatesForWeatherRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            val info = interactor.fetchWeatherFromServerOne(coordinates.latitude, coordinates.longitude)
                ?.mapToPresentation(weatherInfoDomainToPresentationMapper)
            withContext(Dispatchers.Main) { weatherInfo.value = info }
        }
    }

    fun fetchWeatherFromServerTwo(coordinates: CoordinatesForWeatherRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            val info = interactor.fetchWeatherFromServerTwo(coordinates.latitude, coordinates.longitude)
                ?.mapToPresentation(weatherInfoDomainToPresentationMapper)
            withContext(Dispatchers.Main) { weatherInfo.value = info }
        }
    }

    fun fetchWeatherFromServerThree(coordinates: CoordinatesForWeatherRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            val info = interactor.fetchWeatherFromServerThree(coordinates.latitude, coordinates.longitude)
                ?.mapToPresentation(weatherInfoDomainToPresentationMapper)
            withContext(Dispatchers.Main) { weatherInfo.value = info }
        }
    }
}