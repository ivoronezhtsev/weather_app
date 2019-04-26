package ru.voronezhtsev.weatherapp.di;

import dagger.Component;
import ru.voronezhtsev.weatherapp.net.api.WeatherService;

@Component(modules = {RetrofitModule.class})
public interface WeatherComponent {
    WeatherService getWeatherService();
}
