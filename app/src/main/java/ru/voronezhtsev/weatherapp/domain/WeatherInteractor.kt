package ru.voronezhtsev.weatherapp.domain

import io.reactivex.Single
import ru.voronezhtsev.weatherapp.models.domain.Weather

class WeatherInteractor(private val weatherRepository: WeatherRepository) {

    /**
     * Метод загружает погоду по конкретному ИД города из сети, сохраняет данные в БД и возвращает рез-т
     */
    fun loadFromNetwork(cityId: Long) = weatherRepository.loadFromNetwork(cityId)

    val weatherList: Single<List<Weather>>
        get() = weatherRepository.loadFromDb()

}
