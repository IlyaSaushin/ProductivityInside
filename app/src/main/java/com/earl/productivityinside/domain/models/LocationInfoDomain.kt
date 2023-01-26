package com.earl.productivityinside.domain.models

import com.earl.productivityinside.domain.mappers.LocationInfoDomainToUiMapper

interface LocationInfoDomain {

    fun <T> mapToUi(mapper: LocationInfoDomainToUiMapper<T>) : T

    class Base(
        private val continent: String,
        private val country: String,
        private val region: String,
        private val city: String,
        private val latitude: Double,
        private val longitude: Double
    ) : LocationInfoDomain {
        override fun <T> mapToUi(mapper: LocationInfoDomainToUiMapper<T>) =
            mapper.map(continent, country, region, city, latitude, longitude)
    }
}