package com.earl.productivityinside.data

import android.util.Log
import com.earl.productivityinside.data.mappers.LocationDataToDomainMapper
import com.earl.productivityinside.data.mappers.LocationInfoRemoteToDataMapper
import com.earl.productivityinside.data.mappers.WeatherInfoDataToDomainMapper
import com.earl.productivityinside.data.mappers.WeatherInfoRemoteToDataMapper
import com.earl.productivityinside.data.models.LocationInfoData
import com.earl.productivityinside.data.models.WeatherInfoData
import com.earl.productivityinside.data.retrofit.Service
import com.earl.productivityinside.domain.Repository
import com.earl.productivityinside.domain.models.LocationInfoDomain
import com.earl.productivityinside.domain.models.WeatherInfoDomain
import javax.inject.Inject

class BaseRepository @Inject constructor(
    private val service: Service,
    private val jsonParseHelper: JsonParseHelper,
    private val locationInfoRemoteToDataMapper: LocationInfoRemoteToDataMapper<LocationInfoData>,
    private val locationDataToDomainMapper: LocationDataToDomainMapper<LocationInfoDomain>,
    private val weatherInfoRemoteToDataMapper: WeatherInfoRemoteToDataMapper<WeatherInfoData>,
    private val weatherInfoDataToDomainMapper: WeatherInfoDataToDomainMapper<WeatherInfoDomain>
) : Repository {

    override suspend fun fetchLocationByIp(lang: String) = try {
        val jsonString: String = if (lang == RUSSIAN) service.fetchLocationByIpRu(lang).string()
        else service.fetchLocationByIpEn().string()
        jsonParseHelper.parseJsonToLocationInfo(jsonString)
            ?.mapToData(locationInfoRemoteToDataMapper)
            ?.mapToDomain(locationDataToDomainMapper)
    } catch (e: Exception) {
        e.printStackTrace()
        Log.d("tag", "fetchLocationByIp: $e")
        null
    }

    override suspend fun fetchWeatherFromServerOne(lat: Double, long: Double) = try {
        val response = service.fetchWeatherFromServerOne(
            "https://api.openweathermap.org/data/2.5/weather",
            lat,
            long,
            "fd8a9b5c5f69bcfc4b2d0c8057ab48ef",
            "metric"
        ).string()
        Log.d("tag", "fetchWeatherFromServerOne: $response")
        jsonParseHelper.parseJsonToWeatherInfoFromServerOne(response)
            ?.mapToData(weatherInfoRemoteToDataMapper)
            ?.mapToDomain(weatherInfoDataToDomainMapper)
    } catch (e: Exception) {
        e.printStackTrace()
        Log.d("tag", "fetchWeatherFromServerOne: $e")
        null
    }


    override suspend fun fetchWeatherFromServerTwo(lat: Double, long: Double) = try {
        val response = service.fetchWeatherFromServerTwo(
            "https://api.stormglass.io/v2/weather/point",
            "9863d92c-9d58-11ed-b59d-0242ac130002-9863d9f4-9d58-11ed-b59d-0242ac130002",
            lat,
            long,
            listOf("airTemperature", "pressure"),
        ).string()
        Log.d("tag", "fetchWeatherFromServerTwo: $response")
        jsonParseHelper.parseJsonToWeatherInfoFromServerTwo(response)
            ?.mapToData(weatherInfoRemoteToDataMapper)
            ?.mapToDomain(weatherInfoDataToDomainMapper)
    } catch (e: Exception) {
        e.printStackTrace()
        Log.d("tag", "fetchWeatherFromServerTwo: $e")
        null
    }


    override suspend fun fetchWeatherFromServerThree(lat: Double, long: Double) = try {
        val response = service.fetchWeatherFromServerThree(
            "https://api.weather.yandex.ru/v2/forecast/",
            "ecdb281a-7451-49ba-b415-c35b511ec6df",
            lat,
            long
        ).string()
        Log.d("tag", "fetchWeatherFromServerThree: $response")
        jsonParseHelper.parseJsonToWeatherInfoFromServerThree(response)
            ?.mapToData(weatherInfoRemoteToDataMapper)
            ?.mapToDomain(weatherInfoDataToDomainMapper)
    } catch (e: Exception) {
        e.printStackTrace()
        Log.d("tag", "fetchWeatherFromServerThree: $e")
        null
    }

    companion object {
        private const val RUSSIAN = "ru"
    }
}
