package ru.voronezhtsev.weatherapp.domain;

import io.reactivex.Single;
import ru.voronezhtsev.weatherapp.models.domain.LocationInfo;
import ru.voronezhtsev.weatherapp.models.domain.WeatherInfo;

public interface IWeatherRepository {
    Single<WeatherInfo> getWeather(LocationInfo location);
}
