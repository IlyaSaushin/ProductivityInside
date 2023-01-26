package com.earl.productivityinside.domain.mappers

interface LocationInfoDomainToUiMapper<T> {

    fun map(
        continent: String,
        country: String,
        region: String,
        city: String,
        latitude: Double ,
        longitude: Double
    ) : T
}