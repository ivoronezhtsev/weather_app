package ru.voronezhtsev.weatherapp.domain

import io.reactivex.Single
import ru.voronezhtsev.weatherapp.models.domain.Weather

interface IWeatherRepository {
    fun getWeather(cityId: Long): Single<Weather>
    fun load(): List<Weather>
}