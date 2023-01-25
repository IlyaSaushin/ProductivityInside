package com.earl.productivityinside.domain.models

interface LocationInfoDomain {



    class Base(
        private val continent: String,
        private val country: String,
        private val region: String,
        private val city: String
    ) : LocationInfoDomain {

    }
}