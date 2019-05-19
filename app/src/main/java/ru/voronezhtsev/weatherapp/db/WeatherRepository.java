package ru.voronezhtsev.weatherapp.db;

import ru.voronezhtsev.weatherapp.net.api.WeatherService;
import ru.voronezhtsev.weatherapp.net.models.weather.WeatherResponse;
import rx.Single;

public class WeatherRepository {

    private static final String MOSCOW = "524901";
    private static final String APP_ID = "458a017c6453d7ee6e2cfa3a5ddec547";

    private WeatherService mWeatherService;

    public WeatherRepository(WeatherService weatherService) {
        mWeatherService = weatherService;
    }

    private WeatherResponse kelvinToCelcius(WeatherResponse response) {
        //todo Переделать после того как уберу лишние поля, добавить конструктор копирования
        response.getMain().setTemp(response.getMain().getTemp() - 273.15);
        return response;
    }

    public Single<WeatherResponse> getWeather() {
        return mWeatherService.getWeather(MOSCOW, APP_ID).map(this::kelvinToCelcius);
    }
}
