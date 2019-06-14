package ru.voronezhtsev.weatherapp.domain;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import ru.voronezhtsev.weatherapp.data.repositories.LocationRepository;
import ru.voronezhtsev.weatherapp.data.repositories.WeatherRepository;
import ru.voronezhtsev.weatherapp.models.data.network.weather.WeatherResponse;



public class WeatherInteractor {

    private WeatherRepository mWeatherRepository;
    private LocationRepository mLocationRepository;

    public WeatherInteractor(WeatherRepository weatherRepository, LocationRepository locationRepository) {
        mWeatherRepository = weatherRepository;
        mLocationRepository = locationRepository;
    }

    public Single<WeatherResponse> getWeather() {
        return mLocationRepository.getLocation()
                .flatMap(location -> mWeatherRepository.getWeather(location).subscribeOn(Schedulers.io()));
    }
}
