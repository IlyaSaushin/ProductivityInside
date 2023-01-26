package com.earl.productivityinside.data.models.remote

import com.earl.productivityinside.data.mappers.WeatherInfoRemoteToDataMapper
import com.earl.productivityinside.data.models.WeatherInfoData

data class WeatherInfoRemote(
    val temperature: String,
    val pressure: String,
    val weatherIcon: String
) {
    fun mapToData(mapper: WeatherInfoRemoteToDataMapper<WeatherInfoData>) =
        mapper.map(temperature, pressure, weatherIcon)
}
