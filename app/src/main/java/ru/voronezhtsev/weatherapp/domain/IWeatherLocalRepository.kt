package ru.voronezhtsev.weatherapp.domain

import io.reactivex.Completable
import io.reactivex.Maybe
import ru.voronezhtsev.weatherapp.models.domain.Weather

interface IWeatherLocalRepository {
    fun save(weather: Weather): Completable
    val weather: Maybe<Weather>
}