package com.earl.productivityinside.domain

import javax.inject.Inject

interface Interactor {

    suspend fun getLocationByIp(ipAdr: String)

    class Base @Inject constructor(
        private val repository: Repository
    ) : Interactor {
        override suspend fun getLocationByIp(ipAdr: String) {
            repository.getLocationByIp(ipAdr)
        }
    }
}