package com.earl.productivityinside.domain.mappers

interface WeatherInfoDomainToPresentationMapper<T> {

    fun map(
        temperature: String,
        pressure: String,
        weatherIcon: String
    ) : T
}