package com.earl.productivityinside.data.mappers

import com.earl.productivityinside.domain.models.LocationInfoDomain
import javax.inject.Inject

class BaseLocationDataToDomainMapper @Inject constructor() : LocationDataToDomainMapper<LocationInfoDomain> {

    override fun map(
        continent: String,
        country: String,
        region: String,
        city: String
    ) = LocationInfoDomain.Base(continent, country, region, city)
}