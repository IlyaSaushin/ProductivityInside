package com.earl.productivityinside.di

import com.earl.productivityinside.data.retrofit.Service
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun buildService() : Service {
        val interceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
            .create(Service::class.java)
    }

    companion object {
        private const val BASE_URL = "http://ipwho.is/"
//        private const val BASE_URL = "http://ip-api.com/"
    }
}