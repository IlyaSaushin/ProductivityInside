package com.earl.productivityinside.di

import com.earl.productivityinside.data.BaseRepository
import com.earl.productivityinside.data.JsonParseHelper
import com.earl.productivityinside.data.mappers.LocationDataToDomainMapper
import com.earl.productivityinside.data.mappers.LocationInfoRemoteToDataMapper
import com.earl.productivityinside.data.mappers.WeatherInfoDataToDomainMapper
import com.earl.productivityinside.data.mappers.WeatherInfoRemoteToDataMapper
import com.earl.productivityinside.data.models.LocationInfoData
import com.earl.productivityinside.data.models.WeatherInfoData
import com.earl.productivityinside.data.retrofit.Service
import com.earl.productivityinside.domain.Interactor
import com.earl.productivityinside.domain.Repository
import com.earl.productivityinside.domain.models.LocationInfoDomain
import com.earl.productivityinside.domain.models.WeatherInfoDomain
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule {

    @Singleton
    @Provides
    fun provideInteractor(
        repository: Repository
    ) : Interactor {
        return Interactor.Base(
            repository
        )
    }

    @Singleton
    @Provides
    fun provideRepository(
        service: Service,
        jsonParseHelper: JsonParseHelper,
        locationInfoRemoteToDataMapper: LocationInfoRemoteToDataMapper<LocationInfoData>,
        locationDataToDomainMapper: LocationDataToDomainMapper<LocationInfoDomain>,
        weatherInfoRemoteToDataMapper: WeatherInfoRemoteToDataMapper<WeatherInfoData>,
        weatherInfoDataToDomainMapper: WeatherInfoDataToDomainMapper<WeatherInfoDomain>
    ) : Repository {
        return BaseRepository(
            service,
            jsonParseHelper,
            locationInfoRemoteToDataMapper,
            locationDataToDomainMapper,
            weatherInfoRemoteToDataMapper,
            weatherInfoDataToDomainMapper
        )
    }

    @Singleton
    @Provides
    fun provideJsonParseHelper() : JsonParseHelper {
        return JsonParseHelper.Base()
    }
}