package com.earl.productivityinside.data.retrofit

import okhttp3.ResponseBody
import retrofit2.http.GET

interface Service {

    @GET("/")
    suspend fun getLocationByIp(
//        @Query("IP address") ip: String
    ) : ResponseBody
}