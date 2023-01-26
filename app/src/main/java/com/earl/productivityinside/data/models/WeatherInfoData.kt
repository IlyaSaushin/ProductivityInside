package com.earl.productivityinside.data.models

import com.earl.productivityinside.data.mappers.WeatherInfoDataToDomainMapper

interface WeatherInfoData {

    fun <T> mapToDomain(mapper: WeatherInfoDataToDomainMapper<T>) : T

    class Base(
        private val temperature: String,
        private val pressure: String,
        private val weatherIcon: String
    ) : WeatherInfoData {
        override fun <T> mapToDomain(mapper: WeatherInfoDataToDomainMapper<T>) =
            mapper.map(temperature, pressure, weatherIcon)
    }
}