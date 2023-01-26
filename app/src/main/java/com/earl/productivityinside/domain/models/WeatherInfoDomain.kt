package com.earl.productivityinside.domain.models

import com.earl.productivityinside.domain.mappers.WeatherInfoDomainToPresentationMapper

interface WeatherInfoDomain {

    fun <T> mapToPresentation(mapper: WeatherInfoDomainToPresentationMapper<T>) : T
    class Base(
        private val temperature: String,
        private val pressure: String,
        private val weatherIcon: String
    ) : WeatherInfoDomain {

        override fun <T> mapToPresentation(mapper: WeatherInfoDomainToPresentationMapper<T>) =
            mapper.map(temperature, pressure, weatherIcon)
    }
}