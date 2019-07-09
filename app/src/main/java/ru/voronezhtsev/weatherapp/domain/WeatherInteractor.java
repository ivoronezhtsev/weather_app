package ru.voronezhtsev.weatherapp.domain;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.voronezhtsev.weatherapp.data.repositories.WeatherRepository;
import ru.voronezhtsev.weatherapp.models.domain.WeatherInfo;


public class WeatherInteractor {

    private WeatherRepository mWeatherRepository;
    private ILocationRepository mLocationRepository;
    private IWeatherLocalRepository mWeatherLocalRepository;

    public WeatherInteractor(WeatherRepository weatherRepository, ILocationRepository locationRepository,
                             IWeatherLocalRepository weatherLocalRepository) {
        mWeatherRepository = weatherRepository;
        mLocationRepository = locationRepository;
        mWeatherLocalRepository = weatherLocalRepository;
    }

    public Single<WeatherInfo> getWeather() {
        return mLocationRepository.getLocation()
                .flatMap(location -> mWeatherRepository.getWeather(location).subscribeOn(Schedulers.io())
                        .map(weatherInfo -> {
                            mWeatherLocalRepository.save(weatherInfo).subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(() -> {
                                    }, throwable -> {
                                    });
                            return weatherInfo;
                        }));
    }
}
