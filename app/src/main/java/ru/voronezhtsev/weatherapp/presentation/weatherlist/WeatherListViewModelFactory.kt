package ru.voronezhtsev.weatherapp.presentation.weatherlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.voronezhtsev.weatherapp.domain.WeatherInteractor

class WeatherListViewModelFactory(private val weatherInteractor: WeatherInteractor) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = WeatherListViewModel(weatherInteractor) as T
    
}
