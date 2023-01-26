package com.earl.productivityinside.presentation.models

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.earl.productivityinside.R

interface WeatherInfoPresentation {

    fun provideWeatherInfo(
        localeContext: Context,
        tempTextView: TextView,
        pressureTextView: TextView,
        icon: ImageView
    )

    class Base(
        private val temperature: String,
        private val pressure: String,
        private val weatherIcon: String
    ) : WeatherInfoPresentation {

        override fun provideWeatherInfo(localeContext: Context, tempTextView: TextView, pressureTextView: TextView, icon: ImageView) {
            tempTextView.text = localeContext.resources.getString(R.string.temperature, temperature)
            pressureTextView.text = localeContext.resources.getString(R.string.pressure, pressure)
            when(weatherIcon) {
                CLOUDY -> icon.setImageResource(R.drawable.ic_cloudy)
                RAINY -> icon.setImageResource(R.drawable.ic_rainy)
                SUNNY -> icon.setImageResource(R.drawable.ic_sunny)
            }
        }
    }

    companion object {
        private const val CLOUDY = "cloudy"
        private const val RAINY = "rainy"
        private const val SUNNY = "sunny"
    }
}