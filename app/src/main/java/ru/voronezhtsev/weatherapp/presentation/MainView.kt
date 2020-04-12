package ru.voronezhtsev.weatherapp.presentation

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.voronezhtsev.weatherapp.models.presentation.CityModel
import ru.voronezhtsev.weatherapp.models.presentation.WeatherModel

interface MainView : MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showNoPlacesAdded(cityList: List<CityModel>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun addPlace()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showWeather(weather: List<WeatherModel>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showProgressBar()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun hideProgressBar()
}