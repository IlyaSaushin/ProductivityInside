package com.earl.productivityinside.data.models

interface LocationInfoData {



    class Base(
        private val continent: String,
        private val country: String,
        private val region: String,
        private val city: String
    ) : LocationInfoData {

    }
}