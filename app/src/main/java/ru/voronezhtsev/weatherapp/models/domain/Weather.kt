package ru.voronezhtsev.weatherapp.models.domain

data class Weather(val temp: Double,
                   val cityName: String,
                   val iconCode: String,
                   val datetime: Long,
                   val description: String)
