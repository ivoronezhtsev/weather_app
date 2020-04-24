package ru.voronezhtsev.weatherapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.voronezhtsev.weatherapp.domain.ICityRepository
import ru.voronezhtsev.weatherapp.domain.WeatherInteractor

class MainScreenViewModelFactory(private val cityRepository: ICityRepository,
                                 private val mainScreenConverter: MainScreenConverter,
                                 private val weatherInteractor: WeatherInteractor): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainScreenViewModel(cityRepository, mainScreenConverter, weatherInteractor) as T
    }

}