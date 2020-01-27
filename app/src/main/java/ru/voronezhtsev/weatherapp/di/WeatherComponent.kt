package ru.voronezhtsev.weatherapp.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [WeatherModule::class])
interface WeatherComponent {
    val mainPresenterFactory: MainPresenterFactory

    @Component.Builder
    interface Builder {
        fun build(): WeatherComponent
        @BindsInstance
        fun component(context: Context): Builder
    }
}