package ru.voronezhtsev.weatherapp.models.data.network

data class WeatherResponse(val coord: Coord, val main: Main, val weather: List<Weather>, val name: String,
                           val dt: Long,
                           val timezone: Long)

data class Main(val temp: Double)

data class Weather(val id: Int, val icon: String)

data class Coord(val lon: String, val lat: String)