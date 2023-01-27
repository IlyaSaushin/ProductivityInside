package com.earl.productivityinside.data

import com.earl.productivityinside.data.mappers.LocationDataToDomainMapper
import com.earl.productivityinside.data.mappers.LocationInfoRemoteToDataMapper
import com.earl.productivityinside.data.mappers.WeatherInfoDataToDomainMapper
import com.earl.productivityinside.data.mappers.WeatherInfoRemoteToDataMapper
import com.earl.productivityinside.data.models.LocationInfoData
import com.earl.productivityinside.data.models.WeatherInfoData
import com.earl.productivityinside.data.retrofit.NetworkService
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
        null
    }

    override suspend fun fetchWeatherFromServerOne(lat: Double, long: Double) = try {
        val response = service.fetchWeatherFromServerOne(
            NetworkService.SERVER_ONE,
            lat,
            long,
            NetworkService.EndPoints.ServerOneToken.url,
            NetworkService.EndPoints.ServerOneMetrics.url
        ).string()
        jsonParseHelper.parseJsonToWeatherInfoFromServerOne(response)
            ?.mapToData(weatherInfoRemoteToDataMapper)
            ?.mapToDomain(weatherInfoDataToDomainMapper)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }


    override suspend fun fetchWeatherFromServerTwo(lat: Double, long: Double) = try {
        val response = service.fetchWeatherFromServerTwo(
            NetworkService.SERVER_TWO,
            NetworkService.EndPoints.ServerTwoToken.url,
            lat,
            long,
            NetworkService.EndPoints.ServerTwoParams.url,
        ).string()
        jsonParseHelper.parseJsonToWeatherInfoFromServerTwo(response)
            ?.mapToData(weatherInfoRemoteToDataMapper)
            ?.mapToDomain(weatherInfoDataToDomainMapper)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }


    override suspend fun fetchWeatherFromServerThree(lat: Double, long: Double) = try {
        val response = service.fetchWeatherFromServerThree(
            NetworkService.SERVER_THREE,
            NetworkService.EndPoints.ServerThreeToken.url,
            lat,
            long
        ).string()
        jsonParseHelper.parseJsonToWeatherInfoFromServerThree(response)
            ?.mapToData(weatherInfoRemoteToDataMapper)
            ?.mapToDomain(weatherInfoDataToDomainMapper)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

    companion object {
        private const val RUSSIAN = "ru"
    }
}
