package ru.voronezhtsev.weatherapp.view;

import ru.voronezhtsev.weatherapp.db.ForecastsRepository;
import ru.voronezhtsev.weatherapp.db.WeatherRepository;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter {

    private MainView mMainView;
    private WeatherRepository mWeatherRepository;
    private ForecastsRepository mForecastsRepository;

    MainPresenter(WeatherRepository weatherRepository, ForecastsRepository forecastsRepository) {
        mWeatherRepository = weatherRepository;
        mForecastsRepository = forecastsRepository;
    }

    void onAttachView(MainView mainView) {
        mMainView = mainView;
        mWeatherRepository.getWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    mMainView.showTemperature(response.getMain().getTemp());
                }, throwable -> {
                });
        mForecastsRepository.getForecast()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(forecastsResponse -> {
                    mMainView.showForecast(forecastsResponse.getForecast());
                }, error -> {
                });

    }

    void onDetachView() {

    }
}
