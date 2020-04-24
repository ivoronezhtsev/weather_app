package ru.voronezhtsev.weatherapp.domain

import io.reactivex.Single
import ru.voronezhtsev.weatherapp.models.domain.Weather

interface WeatherRepository {

    /**
     * Метод загружает данные о погоде из сети кладет их в БД и возвращает рез-т
     */
    fun loadFromNetwork(cityId: Long): Single<List<Weather>>

    /**
     * Метод загружает данные о погоде из БД
     */
    fun loadFromDb(): Single<List<Weather>>
}