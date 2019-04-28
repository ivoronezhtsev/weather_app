package ru.voronezhtsev.weatherapp.di;

import dagger.Component;
import ru.voronezhtsev.weatherapp.db.ForecastsRepository;
import ru.voronezhtsev.weatherapp.net.api.WeatherService;

@Component(modules = {WeatherModule.class})
public interface WeatherComponent {
    WeatherService getWeatherService();

    ForecastsRepository getForecastsRepository();
}
