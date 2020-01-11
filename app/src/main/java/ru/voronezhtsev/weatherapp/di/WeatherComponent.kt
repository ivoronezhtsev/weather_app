package ru.voronezhtsev.weatherapp.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.voronezhtsev.weatherapp.presentation.CityChoicePresenter
import javax.inject.Singleton

@Singleton
@Component(modules = [WeatherModule::class])
interface WeatherComponent {
    val mainPresenterFactory: MainPresenterFactory
    val cityChoicePresenter: CityChoicePresenter

    @Component.Builder
    interface Builder {
        fun build(): WeatherComponent
        @BindsInstance
        fun component(context: Context): Builder
    }
}