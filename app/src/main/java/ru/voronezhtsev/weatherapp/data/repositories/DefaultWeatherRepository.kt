package ru.voronezhtsev.weatherapp.data.repositories

import android.annotation.SuppressLint
import io.reactivex.Completable
import io.reactivex.Single
import ru.voronezhtsev.weatherapp.data.APP_ID
import ru.voronezhtsev.weatherapp.data.db.WeatherDao
import ru.voronezhtsev.weatherapp.data.remote.WeatherService
import ru.voronezhtsev.weatherapp.domain.WeatherRepository
import ru.voronezhtsev.weatherapp.models.data.network.Main
import ru.voronezhtsev.weatherapp.models.data.network.WeatherResponse
import ru.voronezhtsev.weatherapp.models.domain.Weather
import java.util.*
import java.util.Collections.singletonList

class DefaultWeatherRepository(
        private val weatherService: WeatherService,
        private val weatherDao: WeatherDao) : WeatherRepository {
    @SuppressLint("CheckResult")
    override fun loadFromNetwork(cityId: Long) = weatherService.getWeather(cityId.toString(), APP_ID)
            .flatMap { response ->
                weatherDao.save(convertToDb(response))
                Single.just(singletonList(convert(response)))
            }

    override fun loadFromDb() = Single.fromCallable{weatherDao.load()}

    private fun convert(response: WeatherResponse) = Weather(response.main.temp - 273.15,
            response.name, response.weather[0].icon, Date().time, response.weather[0].description)

    private fun convertToDb(response: WeatherResponse) = WeatherResponse(response.id,
            response.coord, Main(response.main.temp - 273.15), response.weather, response.name,
            Date().time, response.timezone)
}
