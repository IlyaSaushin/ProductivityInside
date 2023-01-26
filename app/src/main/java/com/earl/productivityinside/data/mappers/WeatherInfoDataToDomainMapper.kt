package com.earl.productivityinside.data.mappers

interface WeatherInfoDataToDomainMapper<T> {

    fun map(
        temperature: String,
        pressure: String,
        weatherIcon: String
    ) : T
}