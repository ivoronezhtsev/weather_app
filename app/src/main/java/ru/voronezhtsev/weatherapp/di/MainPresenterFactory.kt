package ru.voronezhtsev.weatherapp.di

import ru.voronezhtsev.weatherapp.domain.ICityRepository
import ru.voronezhtsev.weatherapp.domain.IWeatherRepository
import ru.voronezhtsev.weatherapp.domain.WeatherInteractor
import ru.voronezhtsev.weatherapp.presentation.MainPresenter
import ru.voronezhtsev.weatherapp.presentation.MainScreenConverter

class MainPresenterFactory(private val cityRepository: ICityRepository,
                           private val weatherRepository: IWeatherRepository,
                           private val mainScreenConverter: MainScreenConverter,
                           private val weatherInteractor: WeatherInteractor) {

    fun get(cityId: Long?): MainPresenter = MainPresenter(cityRepository, weatherRepository,
            mainScreenConverter, weatherInteractor, cityId)
}