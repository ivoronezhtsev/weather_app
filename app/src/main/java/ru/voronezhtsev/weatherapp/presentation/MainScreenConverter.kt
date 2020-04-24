package ru.voronezhtsev.weatherapp.presentation

import ru.voronezhtsev.weatherapp.R
import ru.voronezhtsev.weatherapp.models.domain.Weather
import ru.voronezhtsev.weatherapp.models.presentation.WeatherModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToLong

class MainScreenConverter {
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

    private val cityMap = mapOf(
            "Moscow" to "Москва",
            "Vidnoye" to "Видное",
            "Lipetsk" to "Липецк",
            "Lazarevskoye" to "Лазаревское"
    )

    private val description = mapOf(
            "clear sky" to "ясно",
            "few clouds" to "малооблачно",
            "scattered clouds" to "переменная облачность",
            "broken clouds" to "облачно",
            "overcast clouds" to "пасмурно",
            "shower rain" to "ливень",
            "rain" to "дождь",
            "thunderstorm" to "гроза",
            "snow" to "снег",
            "mist" to "туман",
            "light rain" to "небольшой дождь",
            "light shower snow" to "небольшой ливневый снег"
    )

    fun convert(weather: List<Weather>): List<WeatherModel> {
        val weatherList = mutableListOf<WeatherModel>()
        for (w in weather) {
            val formatter = SimpleDateFormat("dd.MM HH:mm", Locale.getDefault());
            weatherList.add(WeatherModel(
                    convertTemp(w.temp),
                    cityMap.getValue(w.cityName),
                    formatter.format(w.datetime),
                    iconMap.getValue(w.iconCode),
                    description.getOrDefault(w.description, w.description)))
        }
        return weatherList
    }

    private fun convertTemp(temp: Double) = if (temp > 0) "+${temp.roundToLong()}" else "-${temp.roundToLong()}"
}