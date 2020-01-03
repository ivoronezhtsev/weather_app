package ru.voronezhtsev.weatherapp.data.repositories

import android.annotation.SuppressLint
import io.reactivex.Single
import ru.voronezhtsev.weatherapp.data.APP_ID
import ru.voronezhtsev.weatherapp.data.remote.WeatherService
import ru.voronezhtsev.weatherapp.domain.IWeatherRepository
import ru.voronezhtsev.weatherapp.models.data.network.WeatherResponse
import ru.voronezhtsev.weatherapp.models.domain.Location
import ru.voronezhtsev.weatherapp.models.domain.Weather

class DefaultWeatherRepository(private val weatherService: WeatherService) : IWeatherRepository {

    override fun getWeather(location: Location) = weatherService.getWeather(location.latitude, location.longitude, APP_ID)
            .flatMap { weatherResponse: WeatherResponse ->
                Single.just(Weather(weatherResponse.main.temp - 273.15, weatherResponse.name,
                        weatherResponse.weather[0].icon))
            }


    @SuppressLint("CheckResult")
    override fun getWeather(cityId: Long) = weatherService.getWeather(cityId.toString(), APP_ID)
            .flatMap { response ->
                Single.just(Weather(response.main.temp - 273.15, response.name,
                        response.weather[0].icon))
            }
}
