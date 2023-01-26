package com.earl.productivityinside.data.mappers

import com.earl.productivityinside.domain.models.WeatherInfoDomain
import javax.inject.Inject

class BaseWeatherInfoDataToDomainMapper @Inject constructor() : WeatherInfoDataToDomainMapper<WeatherInfoDomain> {

    override fun map(temperature: String, pressure: String, weatherIcon: String) =
        WeatherInfoDomain.Base(temperature, pressure, weatherIcon)
}