package com.earl.productivityinside.presentation.mappers

import com.earl.productivityinside.domain.mappers.WeatherInfoDomainToPresentationMapper
import com.earl.productivityinside.presentation.models.WeatherInfoPresentation
import javax.inject.Inject

class BaseWeatherInfoDomainToPresentationMapper @Inject constructor(): WeatherInfoDomainToPresentationMapper<WeatherInfoPresentation> {

    override fun map(temperature: String, pressure: String, weatherIcon: String) =
        WeatherInfoPresentation.Base(temperature, pressure, weatherIcon)
}