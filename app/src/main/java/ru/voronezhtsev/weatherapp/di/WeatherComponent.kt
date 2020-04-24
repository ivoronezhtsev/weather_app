package ru.voronezhtsev.weatherapp.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.voronezhtsev.weatherapp.domain.WeatherInteractor
import ru.voronezhtsev.weatherapp.presentation.MainScreenViewModelFactory
import ru.voronezhtsev.weatherapp.presentation.WeatherListViewModelFactory
import javax.inject.Singleton

@Singleton
@Component(modules = [WeatherModule::class])
interface WeatherComponent {
    val weatherInteractor: WeatherInteractor
    val weatherListViewModelFactory: WeatherListViewModelFactory
    val mainScreenViewModelFactory: MainScreenViewModelFactory

    @Component.Builder
    interface Builder {
        fun build(): WeatherComponent
        @BindsInstance
        fun component(context: Context): Builder
    }
}