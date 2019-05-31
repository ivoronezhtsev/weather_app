package ru.voronezhtsev.weatherapp.db;

import ru.voronezhtsev.weatherapp.net.api.WeatherService;
import ru.voronezhtsev.weatherapp.net.models.weather.WeatherResponse;
import rx.Single;

public class WeatherRepository {

    private static final String MOSCOW = "524901";
    private static final String APP_ID = "458a017c6453d7ee6e2cfa3a5ddec547";

    private WeatherResponse mWeatherResponse;
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
        if (mWeatherResponse != null) {
            return Single.just(mWeatherResponse);
        }
        return Single.fromCallable(() -> {
            synchronized (this) {
                if (mWeatherResponse != null) {
                    return mWeatherResponse;
                }
                mWeatherResponse = mWeatherService.getWeather(MOSCOW, APP_ID)
                        .map(this::kelvinToCelcius)
                        .toBlocking()
                        .value();
                return mWeatherResponse;
            }
        });
        /*return mWeatherService.getWeather(MOSCOW, APP_ID)
                .map(this::kelvinToCelcius)
                .flatMap(weatherResponse -> {
                    mWeatherResponse = weatherResponse;
                    return Single.just(weatherResponse);
                });*/
    }
}
