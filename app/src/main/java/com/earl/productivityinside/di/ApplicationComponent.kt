package com.earl.productivityinside.di

import com.earl.productivityinside.presentation.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class, NetworkModule::class, MappersModule::class])
interface ApplicationComponent {

    fun inject(fragment: MainFragment)
}