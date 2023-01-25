package com.earl.productivityinside.data.mappers

interface LocationDataToDomainMapper <T> {

    fun map(
        continent: String,
        country: String,
        region: String,
        city: String
    ) : T
}