package com.earl.productivityinside.domain

import com.earl.productivityinside.domain.models.LocationInfoDomain
import com.earl.productivityinside.domain.models.WeatherInfoDomain
import javax.inject.Inject

interface Interactor {

    suspend fun fetchLocationByIp(lang: String) : LocationInfoDomain?

    suspend fun fetchWeatherFromServerOne(lat: Double, long: Double) : WeatherInfoDomain?

    suspend fun fetchWeatherFromServerTwo(lat: Double, long: Double) : WeatherInfoDomain?

    suspend fun fetchWeatherFromServerThree(lat: Double, long: Double) : WeatherInfoDomain?

    class Base @Inject constructor(
        private val repository: Repository
    ) : Interactor {

        override suspend fun fetchLocationByIp(lang: String) = repository.fetchLocationByIp(lang)

        override suspend fun fetchWeatherFromServerOne(lat: Double, long: Double) =
            repository.fetchWeatherFromServerOne(lat, long)

        override suspend fun fetchWeatherFromServerTwo(lat: Double, long: Double) =
            repository.fetchWeatherFromServerTwo(lat, long)

        override suspend fun fetchWeatherFromServerThree(lat: Double, long: Double) =
            repository.fetchWeatherFromServerThree(lat, long)
    }
}