package ru.voronezhtsev.weatherapp.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.voronezhtsev.weatherapp.data.repositories.ForecastsRepository;
import ru.voronezhtsev.weatherapp.domain.WeatherInteractor;

@Singleton
@Component(modules = {WeatherModule.class})
public interface WeatherComponent {


    ForecastsRepository getForecastsRepository();

    WeatherInteractor getWeatherInteractor();
}
