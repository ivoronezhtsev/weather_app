package ru.voronezhtsev.weatherapp.models.domain

data class WeatherInfo(
        val temperature: Double,
        val city: String,
        val iconCode: String?
)
