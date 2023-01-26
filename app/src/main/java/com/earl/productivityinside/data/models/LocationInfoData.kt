package com.earl.productivityinside.data.models

import com.earl.productivityinside.data.mappers.LocationDataToDomainMapper

interface LocationInfoData {

    fun <T> mapToDomain(mapper: LocationDataToDomainMapper<T>) : T

    class Base(
        private val continent: String,
        private val country: String,
        private val region: String,
        private val city: String,
        private val latitude: Double,
        private val longitude: Double
    ) : LocationInfoData {
        override fun <T> mapToDomain(mapper: LocationDataToDomainMapper<T>) =
            mapper.map(continent, country, region, city, latitude, longitude)
    }
}