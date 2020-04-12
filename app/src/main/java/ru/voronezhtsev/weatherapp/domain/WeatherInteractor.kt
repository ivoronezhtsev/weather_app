package ru.voronezhtsev.weatherapp.domain

import io.reactivex.Single
import ru.voronezhtsev.weatherapp.models.domain.Weather

class WeatherInteractor(private val weatherRepository: IWeatherRepository) {

    fun getWeather(cityId: Long): Single<List<Weather>> {
        val list = mutableListOf<Weather>()
        return weatherRepository.getWeather(cityId)
                .flatMap {
                    list.addAll(weatherRepository.load())
                    return@flatMap Single.just(list)
                }
    }

    val weatherList: Single<List<Weather>>
        get() {
            return Single.fromCallable {
                return@fromCallable weatherRepository.load()
            }
    }
}
