package ru.voronezhtsev.weatherapp.data.repositories;

import io.reactivex.Single;
import ru.voronezhtsev.weatherapp.data.remote.WeatherResponseConverter;
import ru.voronezhtsev.weatherapp.data.remote.WeatherService;
import ru.voronezhtsev.weatherapp.domain.IWeatherRepository;
import ru.voronezhtsev.weatherapp.models.domain.LocationInfo;
import ru.voronezhtsev.weatherapp.models.domain.WeatherInfo;


public class WeatherRepository implements IWeatherRepository {
    private static final String APP_ID = "458a017c6453d7ee6e2cfa3a5ddec547";

    private WeatherService mWeatherService;

    public WeatherRepository(WeatherService weatherService) {
        mWeatherService = weatherService;
    }

    public Single<WeatherInfo> getWeather(LocationInfo location) {
        WeatherResponseConverter convert = new WeatherResponseConverter();
        return mWeatherService.getWeather(location.getLatitude(), location.getLongitude(), APP_ID)
                .map(convert::convert);
    }
}
