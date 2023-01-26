package com.earl.productivityinside.data.mappers

import com.earl.productivityinside.data.models.LocationInfoData
import javax.inject.Inject

class BaseLocationRemoteToDataMapper @Inject constructor() : LocationInfoRemoteToDataMapper<LocationInfoData> {

    override fun map(
        continent: String,
        country: String,
        region: String,
        city: String,
        latitude: Double ,
        longitude: Double
    ) = LocationInfoData.Base(
        continent, country, region, city, latitude, longitude
    )
}