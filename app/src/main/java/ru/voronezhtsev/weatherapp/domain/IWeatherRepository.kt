package ru.voronezhtsev.weatherapp.domain

import io.reactivex.Single
import ru.voronezhtsev.weatherapp.models.domain.Location
import ru.voronezhtsev.weatherapp.models.domain.Weather

interface IWeatherRepository {
    fun getWeather(location: Location): Single<Weather>
}