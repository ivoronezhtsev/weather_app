package ru.voronezhtsev.weatherapp;

import android.location.Location;
import ru.voronezhtsev.weatherapp.db.LocationRepository;
import ru.voronezhtsev.weatherapp.db.WeatherRepository;
import ru.voronezhtsev.weatherapp.net.models.weather.WeatherResponse;
import rx.Single;
import rx.functions.Func1;


public class WeatherInteractor {

    private WeatherRepository mWeatherRepository;
    private LocationRepository mLocationRepository;

    public WeatherInteractor(WeatherRepository weatherRepository, LocationRepository locationRepository) {
        mWeatherRepository = weatherRepository;
        mLocationRepository = locationRepository;
    }

    public Single<WeatherResponse> getWeather() {
        return mLocationRepository.getLocation()
                .flatMap((Func1<Location, Single<WeatherResponse>>)
                        location -> mWeatherRepository.getWeather(location));
    }
}
