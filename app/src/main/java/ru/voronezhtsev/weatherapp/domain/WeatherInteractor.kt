package ru.voronezhtsev.weatherapp.domain

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.voronezhtsev.weatherapp.models.domain.Location
import ru.voronezhtsev.weatherapp.models.domain.Weather

class WeatherInteractor(private val weatherRepository: IWeatherRepository,
                        private val locationRepository: ILocationRepository,
                        private val weatherLocalRepository: IWeatherLocalRepository) {

    fun weather(): Single<Weather> =
            locationRepository.location
                .flatMap { location: Location? ->
                    weatherRepository.getWeather(location!!).subscribeOn(Schedulers.io())
                            .map { weather: Weather ->
                                weatherLocalRepository.save(weather).subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe({}) { throwable: Throwable? -> }
                                weather
                            }
                }

    fun weather(cityId: Long): Single<Weather> = weatherRepository.getWeather(cityId)
}
