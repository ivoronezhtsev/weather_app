package ru.voronezhtsev.weatherapp.models.presentation

import androidx.annotation.DrawableRes

data class WeatherModel(
        val temp: String,
        val city: String,
        @DrawableRes
        val icon: Int
)