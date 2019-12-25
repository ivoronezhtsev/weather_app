package ru.voronezhtsev.weatherapp.models.data.network

data class WeatherResponse(val main: Main, val weather: List<Weather>, val name: String)

data class Main(val temp: Double)

data class Weather(val icon: String)