package ru.voronezhtsev.weatherapp.di

import ru.voronezhtsev.weatherapp.data.repositories.ForecastsRepository
import ru.voronezhtsev.weatherapp.domain.ICityRepository
import ru.voronezhtsev.weatherapp.domain.IWeatherRepository
import ru.voronezhtsev.weatherapp.domain.WeatherInteractor
import ru.voronezhtsev.weatherapp.presentation.MainPresenter
import ru.voronezhtsev.weatherapp.presentation.MainScreenConverter

class MainPresenterFactory(private val weatherInteractor: WeatherInteractor,
                           private val forecastsRepository: ForecastsRepository,
                           private val cityRepository: ICityRepository,
                           private val weatherRepository: IWeatherRepository,
                           private val mainScreenConverter: MainScreenConverter) {

    fun get(cityId: Long?): MainPresenter = MainPresenter(weatherInteractor, forecastsRepository, cityRepository, weatherRepository,
            mainScreenConverter, cityId)
}