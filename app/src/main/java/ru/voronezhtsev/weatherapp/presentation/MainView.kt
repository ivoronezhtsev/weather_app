package ru.voronezhtsev.weatherapp.presentation

import com.arellomobile.mvp.MvpView
import ru.voronezhtsev.weatherapp.models.data.network.forecast.Forecast
import ru.voronezhtsev.weatherapp.models.domain.WeatherInfo

interface MainView : MvpView {

    fun showWeather(weatherInfo: WeatherInfo)

    fun showForecast(forecast: List<Forecast>)
}