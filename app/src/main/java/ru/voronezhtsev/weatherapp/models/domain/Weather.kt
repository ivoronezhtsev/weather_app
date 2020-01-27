package ru.voronezhtsev.weatherapp.models.domain

data class Weather(val temp: Double, val city: String, val iconCode: String, val datetime: Long)
