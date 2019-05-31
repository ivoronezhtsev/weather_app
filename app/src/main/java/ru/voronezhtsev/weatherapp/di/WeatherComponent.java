package ru.voronezhtsev.weatherapp.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.voronezhtsev.weatherapp.db.ForecastsRepository;
import ru.voronezhtsev.weatherapp.db.WeatherRepository;
import ru.voronezhtsev.weatherapp.net.api.WeatherService;

@Singleton
@Component(modules = {WeatherModule.class})
public interface WeatherComponent {
    WeatherService getWeatherService();

    ForecastsRepository getForecastsRepository();

    WeatherRepository getWeatherRepository();
}
