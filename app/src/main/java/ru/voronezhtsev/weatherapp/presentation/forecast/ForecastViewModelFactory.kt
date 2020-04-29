package ru.voronezhtsev.weatherapp.presentation.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.voronezhtsev.weatherapp.domain.ForecastInteractor
import ru.voronezhtsev.weatherapp.presentation.MainScreenConverter

class ForecastViewModelFactory(private val forecastInteractor: ForecastInteractor,
                               private val mainScreenConverter: MainScreenConverter) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ForecastViewModel(forecastInteractor, mainScreenConverter) as T
    }

}
