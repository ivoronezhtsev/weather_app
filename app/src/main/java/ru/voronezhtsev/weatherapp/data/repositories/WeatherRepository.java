package ru.voronezhtsev.weatherapp.data.repositories;


import io.reactivex.Single;
import ru.voronezhtsev.weatherapp.data.remote.WeatherService;
import ru.voronezhtsev.weatherapp.models.data.network.weather.WeatherResponse;
import ru.voronezhtsev.weatherapp.models.domain.LocationEntity;


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

    public Single<WeatherResponse> getWeather(LocationEntity locationEntity) {
        return mWeatherService.getWeather(locationEntity.getLatitude(), locationEntity.getLongitude(), APP_ID)
                .map(this::kelvinToCelcius);
    }
}
