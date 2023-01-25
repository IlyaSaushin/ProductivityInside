package com.earl.productivityinside.di

import com.earl.productivityinside.data.mappers.BaseLocationDataToDomainMapper
import com.earl.productivityinside.data.mappers.BaseLocationRemoteToDataMapper
import com.earl.productivityinside.data.mappers.LocationDataToDomainMapper
import com.earl.productivityinside.data.mappers.LocationInfoRemoteToDataMapper
import com.earl.productivityinside.data.models.LocationInfoData
import com.earl.productivityinside.domain.models.LocationInfoDomain
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
}