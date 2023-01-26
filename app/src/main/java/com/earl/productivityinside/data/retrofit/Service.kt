package com.earl.productivityinside.data.retrofit

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface Service {

    @GET("/")
    suspend fun fetchLocationByIpEn() : ResponseBody

    @GET("/")
    suspend fun fetchLocationByIpRu(@Query("lang") lang: String) : ResponseBody

    // https://openweathermap.org/current
    @GET
    suspend fun fetchWeatherFromServerOne(
        @Url url: String,
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") appId: String,
        @Query("units") units: String,
    ) : ResponseBody

    // https://docs.stormglass.io/#/weather  -  only 10 requests per day !!!
    @GET
    suspend fun fetchWeatherFromServerTwo(
        @Url url: String,
        @Header("Authorization") appToken: String,
        @Query("lat") latitude: Double,
        @Query("lng") longitude: Double,
        @Query("params", encoded = true) temp: List<String>,
    ) : ResponseBody

    // https://yandex.com/dev/weather/
    @GET
    suspend fun fetchWeatherFromServerThree(
        @Url url: String,
        @Header ("X-Yandex-API-Key") token: String,
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ) : ResponseBody
}