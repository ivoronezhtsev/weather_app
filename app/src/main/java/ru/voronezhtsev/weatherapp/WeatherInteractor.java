package ru.voronezhtsev.weatherapp;

import io.reactivex.Single;
import ru.voronezhtsev.weatherapp.db.LocationRepository;
import ru.voronezhtsev.weatherapp.db.WeatherRepository;
import ru.voronezhtsev.weatherapp.net.models.weather.WeatherResponse;


public class WeatherInteractor {

    private WeatherRepository mWeatherRepository;
    private LocationRepository mLocationRepository;

    public WeatherInteractor(WeatherRepository weatherRepository, LocationRepository locationRepository) {
        mWeatherRepository = weatherRepository;
        mLocationRepository = locationRepository;
    }

    public Single<WeatherResponse> getWeather() {
        return mWeatherRepository.getWeather(mLocationRepository.getLocation().toBlocking().first());
    }
}
