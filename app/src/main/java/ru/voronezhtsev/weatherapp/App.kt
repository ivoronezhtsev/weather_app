package ru.voronezhtsev.weatherapp

import android.app.Application
import ru.voronezhtsev.weatherapp.di.DaggerWeatherComponent
import ru.voronezhtsev.weatherapp.di.WeatherComponent
import ru.voronezhtsev.weatherapp.di.WeatherModule

class App : Application() {
    companion object {
        lateinit var component: WeatherComponent private set
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerWeatherComponent.builder().weatherModule(WeatherModule(this)).build()
    }
}