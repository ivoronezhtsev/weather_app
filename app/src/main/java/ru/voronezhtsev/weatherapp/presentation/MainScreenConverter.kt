package ru.voronezhtsev.weatherapp.presentation

import ru.voronezhtsev.weatherapp.R
import ru.voronezhtsev.weatherapp.models.domain.Weather
import ru.voronezhtsev.weatherapp.models.presentation.WeatherModel
import kotlin.math.roundToLong

class MainScreenConverter {
    private val iconMap = mapOf<String, Int>("13d" to R.drawable.icon13d,
            "13n" to R.drawable.icon13d,
            "02d" to R.drawable.icon02d,
            "01n" to R.drawable.icon01n,
            "01d" to R.drawable.icon01d)

    fun convert(weather: Weather): WeatherModel {
        return WeatherModel(weather.temp.roundToLong().toString(), weather.city, iconMap.getValue(weather.iconCode))
    }
}