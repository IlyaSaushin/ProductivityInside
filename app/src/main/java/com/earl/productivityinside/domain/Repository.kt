package com.earl.productivityinside.domain

import com.earl.productivityinside.domain.models.LocationInfoDomain
import com.earl.productivityinside.domain.models.WeatherInfoDomain

interface Repository {

    suspend fun fetchLocationByIp(lang: String) : LocationInfoDomain?

    suspend fun fetchWeatherFromServerOne(lat: Double, long: Double) : WeatherInfoDomain?

    suspend fun fetchWeatherFromServerTwo(lat: Double, long: Double) : WeatherInfoDomain?

    suspend fun fetchWeatherFromServerThree(lat: Double, long: Double) : WeatherInfoDomain?
}