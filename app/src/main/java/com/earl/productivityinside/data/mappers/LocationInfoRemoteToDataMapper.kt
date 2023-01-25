package com.earl.productivityinside.data.mappers

interface LocationInfoRemoteToDataMapper<T> {

    fun map(
        continent: String,
        country: String,
        region: String,
        city: String
    ) : T
}