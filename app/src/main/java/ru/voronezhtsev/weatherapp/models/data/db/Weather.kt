package ru.voronezhtsev.weatherapp.models.data.db

data class Weather(val cityId: Long, val temp: Double, val iconCode: String, val datetime: Long)