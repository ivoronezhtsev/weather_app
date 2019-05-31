package ru.voronezhtsev.weatherapp;

import android.app.Application;

import ru.voronezhtsev.weatherapp.di.DaggerWeatherComponent;
import ru.voronezhtsev.weatherapp.di.WeatherComponent;
import ru.voronezhtsev.weatherapp.di.WeatherModule;

public class App extends Application {
    private static App sInstance;
    private WeatherComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = DaggerWeatherComponent.builder().weatherModule(new WeatherModule(this)).build();
        sInstance = this;
    }

    public WeatherComponent getWeatherComponent() {
        return mComponent;
    }

    public static App getInstance() {
        return sInstance;
    }
}
