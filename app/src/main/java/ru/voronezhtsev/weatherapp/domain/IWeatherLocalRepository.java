package ru.voronezhtsev.weatherapp.domain;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import ru.voronezhtsev.weatherapp.models.domain.WeatherInfo;

public interface IWeatherLocalRepository {

    Completable save(WeatherInfo weatherInfo);

    Maybe<WeatherInfo> getWeather();
}
