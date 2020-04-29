package ru.voronezhtsev.weatherapp.domain

import io.reactivex.Single
import ru.voronezhtsev.weatherapp.models.domain.Forecast

interface ForecastsRepository {
    /**
     * Метод загружает данные о погоде из сети кладет их в БД и возвращает рез-т
     */
    fun loadFromNetwork(cityId: Long): Single<List<Forecast>>

    /**
     * Метод загружает данные о погоде из БД
     */
    fun loadFromDb(cityName: String): Single<List<Forecast>>
}