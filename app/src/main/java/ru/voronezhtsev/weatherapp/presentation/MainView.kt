package ru.voronezhtsev.weatherapp.presentation

import com.arellomobile.mvp.MvpView
import ru.voronezhtsev.weatherapp.models.presentation.CityModel
import ru.voronezhtsev.weatherapp.models.presentation.WeatherModel

interface MainView : MvpView {
    fun showNoPlacesAdded(cityList: List<CityModel>)
    fun addPlace()
    fun showWeather(weather: List<WeatherModel>)
}