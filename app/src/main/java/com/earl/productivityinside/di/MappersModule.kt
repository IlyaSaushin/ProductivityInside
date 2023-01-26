package com.earl.productivityinside.di

import com.earl.productivityinside.data.mappers.*
import com.earl.productivityinside.data.models.LocationInfoData
import com.earl.productivityinside.data.models.WeatherInfoData
import com.earl.productivityinside.domain.mappers.LocationInfoDomainToUiMapper
import com.earl.productivityinside.domain.mappers.WeatherInfoDomainToPresentationMapper
import com.earl.productivityinside.domain.models.LocationInfoDomain
import com.earl.productivityinside.domain.models.WeatherInfoDomain
import com.earl.productivityinside.presentation.mappers.BaseLocationInfoDomainToUiMapper
import com.earl.productivityinside.presentation.mappers.BaseWeatherInfoDomainToPresentationMapper
import com.earl.productivityinside.presentation.models.LocationInfoPresentation
import com.earl.productivityinside.presentation.models.WeatherInfoPresentation
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MappersModule {

    @Singleton
    @Provides
    fun provideLocationInfoDataToDomainMapper() : LocationDataToDomainMapper<LocationInfoDomain> {
        return BaseLocationDataToDomainMapper()
    }

    @Singleton
    @Provides
    fun provideLocationRemoteToDataMapper() : LocationInfoRemoteToDataMapper<LocationInfoData> {
        return BaseLocationRemoteToDataMapper()
    }

    @Singleton
    @Provides
    fun provideLocationInfoDomainToPresentationMapper() : LocationInfoDomainToUiMapper<LocationInfoPresentation> {
        return BaseLocationInfoDomainToUiMapper()
    }

    @Singleton
    @Provides
    fun provideWeatherInfoRemoteToDataMapper() : WeatherInfoRemoteToDataMapper<WeatherInfoData> {
        return BaseWeatherInfoRemoteToDataMapper()
    }

    @Singleton
    @Provides
    fun provideWeatherInfoDataToDomainMapper() : WeatherInfoDataToDomainMapper<WeatherInfoDomain> {
        return BaseWeatherInfoDataToDomainMapper()
    }

    @Singleton
    @Provides
    fun provideWeatherInfoDomainToPresentationMapper() : WeatherInfoDomainToPresentationMapper<WeatherInfoPresentation> {
        return BaseWeatherInfoDomainToPresentationMapper()
    }
}