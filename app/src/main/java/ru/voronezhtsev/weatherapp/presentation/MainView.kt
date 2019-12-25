package ru.voronezhtsev.weatherapp.presentation

import com.arellomobile.mvp.MvpView
import ru.voronezhtsev.weatherapp.models.data.network.Forecast
import ru.voronezhtsev.weatherapp.models.presentation.WeatherModel

interface MainView : MvpView {

    fun showWeather(weather: WeatherModel)

    fun showForecast(forecast: List<Forecast>)
}