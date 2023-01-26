package com.earl.productivityinside.data

import android.util.Log
import com.earl.productivityinside.data.models.remote.LocationInfoRemote
import com.earl.productivityinside.data.models.remote.WeatherInfoRemote
import org.json.JSONObject
import javax.inject.Inject

interface JsonParseHelper {

    fun parseJsonToLocationInfo(json: String) : LocationInfoRemote?

    fun parseJsonToWeatherInfoFromServerOne(json: String) : WeatherInfoRemote?

    fun parseJsonToWeatherInfoFromServerTwo(json: String) : WeatherInfoRemote?

    fun parseJsonToWeatherInfoFromServerThree(json: String) : WeatherInfoRemote?

    class Base @Inject constructor() : JsonParseHelper {

        override fun parseJsonToLocationInfo(json: String) : LocationInfoRemote? {
            val jsonObject = JSONObject(json)
            return if (jsonObject.getBoolean(IS_SUCCESS)) {
                LocationInfoRemote(
                    jsonObject.getString(CONTINENT),
                    jsonObject.getString(COUNTRY),
                    jsonObject.getString(REGION),
                    jsonObject.getString(CITY),
                    jsonObject.getDouble(LATITUDE),
                    jsonObject.getDouble(LONGITUDE),
                )
            } else null
        }

        override fun parseJsonToWeatherInfoFromServerOne(json: String): WeatherInfoRemote? {
            val jsonObject = JSONObject(json)
            val mainWeatherObject = jsonObject.getJSONObject(MAIN_WEATHER_SERVER_ONE)
            val weatherFactObject = jsonObject.getJSONArray(FACT_WEATHER_SERVER_ONE).getJSONObject(0)
            val correctPressure = mainWeatherObject.getString(PRESSURE_SERVER_ONE).toDouble() * special_num
            return if (jsonObject != null) {
                val icon = when(weatherFactObject.getString(ICON_SERVER_ONE)) {
                    "Clouds" -> "cloudy"
                    "Rain" -> "rainy"
                    "Sun" -> "sunny"
                    else -> "sunny"
                }
                WeatherInfoRemote(
                    mainWeatherObject.getString(TEMPERATURE_SERVER_ONE).toString(),
                    String.format("%.2f", correctPressure),
                    icon
                )
            } else null
        }

        override fun parseJsonToWeatherInfoFromServerTwo(json: String): WeatherInfoRemote? {
            val jsonObject = JSONObject(json)
            val usefulObject = jsonObject.getJSONArray(JSON_OBJECT_SERVER_TWO).getJSONObject(0)
                .getJSONObject(WEATHER_OBJECT_SERVER_TWO)
            return if (jsonObject != null) {
                val icon = when(jsonObject.getString(ICON_SERVER_TWO)) {
                    "Clouds" -> "cloudy"
                    "Rain" -> "rainy"
                    "Sun" -> "sunny"
                    else -> "sunny"
                }
                WeatherInfoRemote(
                    jsonObject.getString(TEMPERATURE_SERVER_TWO),
                    jsonObject.getString(PRESSURE_SERVER_TWO),
                    icon
                )
            } else null
        }

        override fun parseJsonToWeatherInfoFromServerThree(json: String): WeatherInfoRemote? {
            val jsonObject = JSONObject(json)
            val factWeather = jsonObject.getJSONObject(FACT_OBJECT_SERVER_THREE)
            return if (factWeather != null) {
                val icon = when(factWeather.getString(ICON_SERVER_THREE)) {
                    "cloudy" -> "cloudy"
                    "rain" -> "rainy"
                    "clear" -> "sunny"
                    else -> "sunny"
                }
                val weather = WeatherInfoRemote(
                    factWeather.getString(TEMPERATURE_SERVER_THREE),
                    factWeather.getString(PRESSURE_SERVER_THREE),
                    icon
                )
                Log.d("tag", "parseJsonToWeatherInfoFromServerThree: $weather")
                weather
            } else null
        }
    }

    companion object {
        private const val IS_SUCCESS = "success"
        private const val CONTINENT = "continent"
        private const val COUNTRY = "country"
        private const val REGION = "region"
        private const val CITY = "city"
        private const val LATITUDE = "latitude"
        private const val LONGITUDE = "longitude"
        private const val FACT_WEATHER_SERVER_ONE = "weather"
        private const val MAIN_WEATHER_SERVER_ONE = "main"
        private const val TEMPERATURE_SERVER_ONE = "temp"
        private const val PRESSURE_SERVER_ONE = "pressure"
        private const val ICON_SERVER_ONE = "main"
        private const val JSON_OBJECT_SERVER_TWO = "hours"
        private const val WEATHER_OBJECT_SERVER_TWO = "airTemperature"
        private const val TEMPERATURE_SERVER_TWO = "noaa"
        private const val PRESSURE_SERVER_TWO = "sg"
        private const val ICON_SERVER_TWO = "pressure"
        private const val FACT_OBJECT_SERVER_THREE = "fact"
        private const val TEMPERATURE_SERVER_THREE = "temp"
        private const val PRESSURE_SERVER_THREE = "pressure_mm"
        private const val ICON_SERVER_THREE = "condition"
        private const val special_num = 0.750063755419211
    }
}