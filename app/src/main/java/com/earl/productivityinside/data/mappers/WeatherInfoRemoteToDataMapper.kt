package com.earl.productivityinside.data.mappers

interface WeatherInfoRemoteToDataMapper<T> {

    fun map(
        temperature: String,
        pressure: String,
        weatherIcon: String
    ) : T
}