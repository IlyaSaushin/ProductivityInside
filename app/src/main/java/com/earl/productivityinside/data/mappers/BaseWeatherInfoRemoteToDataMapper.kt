package com.earl.productivityinside.data.mappers

import com.earl.productivityinside.data.models.WeatherInfoData
import javax.inject.Inject

class BaseWeatherInfoRemoteToDataMapper @Inject constructor(): WeatherInfoRemoteToDataMapper<WeatherInfoData> {

    override fun map(temperature: String, pressure: String, weatherIcon: String) =
        WeatherInfoData.Base(temperature, pressure, weatherIcon)
}