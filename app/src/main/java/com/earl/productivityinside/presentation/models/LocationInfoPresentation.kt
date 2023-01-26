package com.earl.productivityinside.presentation.models

import android.content.Context
import android.widget.TextView
import com.earl.productivityinside.R

interface LocationInfoPresentation {

    fun provideLocationInfo(
        context: Context,
        continentText: TextView,
        countryText: TextView,
        regionText: TextView,
        cityText: TextView,
    )

    fun provideCoordinates() : CoordinatesForWeatherRequest

    class Base(
        private val continent: String,
        private val country: String,
        private val region: String,
        private val city: String,
        private val latitude: Double,
        private val longitude: Double
    ) : LocationInfoPresentation {

        override fun provideLocationInfo(
            context: Context,
            continentText: TextView,
            countryText: TextView,
            regionText: TextView,
            cityText: TextView
        ) {
            continentText.text = context.resources.getString(R.string.continent, continent)
            countryText.text = context.resources.getString(R.string.county, country)
            regionText.text = context.resources.getString(R.string.region, region)
            cityText.text = city
        }

        override fun provideCoordinates() = CoordinatesForWeatherRequest(latitude, longitude)
    }
}