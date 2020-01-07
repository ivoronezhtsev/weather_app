package ru.voronezhtsev.weatherapp

import android.app.Application
import ru.voronezhtsev.weatherapp.di.DaggerWeatherComponent
import ru.voronezhtsev.weatherapp.di.WeatherComponent

class App : Application() {
    companion object {
        lateinit var component: WeatherComponent private set
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerWeatherComponent.builder().component(this).build();
    }
}