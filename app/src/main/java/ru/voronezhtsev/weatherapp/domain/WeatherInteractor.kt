package ru.voronezhtsev.weatherapp.domain

import android.annotation.SuppressLint
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.voronezhtsev.weatherapp.models.domain.Location
import ru.voronezhtsev.weatherapp.models.domain.Weather

class WeatherInteractor(private val weatherRepository: IWeatherRepository,
                        private val locationRepository: ILocationRepository,
                        private val weatherLocalRepository: IWeatherLocalRepository) {

    val weather: Single<Weather>
        get() = locationRepository.location
                .flatMap { location: Location? ->
                    weatherRepository.getWeather(location!!).subscribeOn(Schedulers.io())
                            .map { weather: Weather ->
                                weatherLocalRepository.save(weather).subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe({}) { throwable: Throwable? -> }
                                weather
                            }
                }
    @SuppressLint("CheckResult")
    fun getWeather(cityId: Long): Single<Weather> {
        return weatherRepository.getWeather(cityId).subscribeOn(Schedulers.io())
                .map { weather: Weather ->
                    weatherLocalRepository.save(weather).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({}) { throwable: Throwable? -> }
                    weather
                }
    }
}
