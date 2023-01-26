package com.earl.productivityinside.presentation.mappers

import com.earl.productivityinside.domain.mappers.LocationInfoDomainToUiMapper
import com.earl.productivityinside.presentation.models.LocationInfoPresentation
import javax.inject.Inject

class BaseLocationInfoDomainToUiMapper @Inject constructor() : LocationInfoDomainToUiMapper<LocationInfoPresentation> {

    override fun map(
        continent: String,
        country: String,
        region: String,
        city: String,
        latitude: Double ,
        longitude: Double
    ) = LocationInfoPresentation.Base(
        continent, country, region, city, latitude, longitude
    )
}