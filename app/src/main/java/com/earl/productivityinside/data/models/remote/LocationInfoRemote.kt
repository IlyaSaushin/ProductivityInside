package com.earl.productivityinside.data.models.remote

import com.earl.productivityinside.data.mappers.LocationInfoRemoteToDataMapper
import com.earl.productivityinside.data.models.LocationInfoData
import kotlinx.serialization.Serializable

@Serializable
data class LocationInfoRemote(
    val continent: String,
    val country: String,
    val region: String,
    val city: String,
    val latitude: Double,
    val longitude: Double
) {
    fun mapToData(mapper: LocationInfoRemoteToDataMapper<LocationInfoData>) =
        mapper.map(continent, country, region, city, latitude, longitude)
}