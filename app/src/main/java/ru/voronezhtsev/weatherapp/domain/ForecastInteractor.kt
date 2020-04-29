package ru.voronezhtsev.weatherapp.domain

class ForecastInteractor(private val forecastsRepository: ForecastsRepository) {

    fun getForecast(cityName: String) = forecastsRepository.loadFromDb(cityName)

}