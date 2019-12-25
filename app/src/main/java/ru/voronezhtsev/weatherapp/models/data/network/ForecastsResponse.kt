package ru.voronezhtsev.weatherapp.models.data.network

data class ForecastsResponse(val cod: String,
                             val message: Double,
                             val cnt: Int,
                             val forecast: List<Forecast>,
                             val city: City)

data class Forecast(val dt: Long, val main: Main)

data class City(val id: Int, val name: String)
