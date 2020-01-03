package ru.voronezhtsev.weatherapp.presentation

import com.arellomobile.mvp.MvpView

interface CityChoiceView: MvpView {
    fun showInputCityWidget(list: Map<String, Long>)
    fun runWeatherScreen(cityId: Long)
}