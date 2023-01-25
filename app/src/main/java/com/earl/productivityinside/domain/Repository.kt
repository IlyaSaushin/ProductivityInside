package com.earl.productivityinside.domain

import com.earl.productivityinside.domain.models.LocationInfoDomain

interface Repository {

    suspend fun getLocationByIp(ipAdr: String) : LocationInfoDomain
}