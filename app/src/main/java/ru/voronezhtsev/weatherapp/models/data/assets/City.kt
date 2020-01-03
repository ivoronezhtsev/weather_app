package ru.voronezhtsev.weatherapp.models.data.assets

data class City(val id: String, val name: String, val country: String, val coord: Coord)

data class Coord(val lon: String, val lat: String)