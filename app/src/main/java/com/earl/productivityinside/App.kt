package com.earl.productivityinside

import android.app.Application
import com.earl.productivityinside.di.DaggerApplicationComponent

class App : Application() {

    val appComponent = DaggerApplicationComponent.create()
}