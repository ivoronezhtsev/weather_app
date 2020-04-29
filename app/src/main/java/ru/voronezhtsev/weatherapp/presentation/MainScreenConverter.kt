package ru.voronezhtsev.weatherapp.presentation

import ru.voronezhtsev.weatherapp.R
import ru.voronezhtsev.weatherapp.models.domain.Forecast
import ru.voronezhtsev.weatherapp.models.domain.Weather
import ru.voronezhtsev.weatherapp.models.presentation.ForecastModel
import ru.voronezhtsev.weatherapp.models.presentation.WeatherModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToLong

class MainScreenConverter {
    private val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
    private val iconMap = mapOf("13d" to R.drawable.icon13d,
            "01d" to R.drawable.icon01d,
            "01n" to R.drawable.icon01n,
            "02d" to R.drawable.icon02d,
            "02n" to R.drawable.icon02n,
            "03d" to R.drawable.icon03d,
            "03n" to R.drawable.icon03d,
            "04d" to R.drawable.icon04d,
            "04n" to R.drawable.icon04d,
            "09n" to R.drawable.icon09n,
            "09d" to R.drawable.icon09n,
            "10d" to R.drawable.icon10d,
            "10n" to R.drawable.icon10n,
            "11d" to R.drawable.icon11d,
            "11n" to R.drawable.icon11d,
            "13d" to R.drawable.icon13d,
            "13n" to R.drawable.icon13d,
            "50d" to R.drawable.icon50d,
            "50n" to R.drawable.icon50d)


    fun convert(weather: List<Weather>): List<WeatherModel> {
        val weatherList = mutableListOf<WeatherModel>()
        for (w in weather) {
            weatherList.add(WeatherModel(
                    convertTemp(w.temp),
                    w.cityName,
                    formatter.format(w.datetime),
                    iconMap.getValue(w.iconCode),
                    w.description))
        }
        return weatherList
    }

    fun convertForecast(forecast: List<Forecast>): List<ForecastModel> {
        val result = mutableListOf<ForecastModel>()
        for (f in forecast) {
            result.add(ForecastModel(formatter.format(f.dateTime * 1000), iconMap.getValue(f.iconCode),
                    convertTemp(f.temp), f.description))
        }
        return result
    }

    private fun convertTemp(temp: Double) = if (temp > 0) "+${temp.roundToLong()}" else "-${temp.roundToLong()}"
}