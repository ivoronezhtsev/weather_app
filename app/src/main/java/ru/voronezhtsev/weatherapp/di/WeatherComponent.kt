package ru.voronezhtsev.weatherapp.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.voronezhtsev.weatherapp.presentation.MainScreenViewModelFactory
import ru.voronezhtsev.weatherapp.presentation.forecast.ForecastViewModelFactory
import ru.voronezhtsev.weatherapp.presentation.weatherlist.WeatherListViewModelFactory
import javax.inject.Singleton

@Singleton
@Component(modules = [WeatherModule::class])
interface WeatherComponent {
    val weatherListViewModelFactory: WeatherListViewModelFactory
    val mainScreenViewModelFactory: MainScreenViewModelFactory
    val forecastViewModelFactory: ForecastViewModelFactory

    @Component.Builder
    interface Builder {
        fun build(): WeatherComponent
        @BindsInstance
        fun component(context: Context): Builder
    }
}