package ru.voronezhtsev.weatherapp.models.domain

data class Forecast(val cityName: String,
                    val dateTime: Long,
                    val temp: Double,
                    val iconCode: String,
                    val description: String
)