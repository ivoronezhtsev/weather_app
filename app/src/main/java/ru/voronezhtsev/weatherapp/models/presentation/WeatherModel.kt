package ru.voronezhtsev.weatherapp.models.presentation

import androidx.annotation.DrawableRes

data class WeatherModel(
        val temp: String,
        val city: String,
        val lon: Double,
        val lat: Double,
        @DrawableRes
        val icon: Int?
)