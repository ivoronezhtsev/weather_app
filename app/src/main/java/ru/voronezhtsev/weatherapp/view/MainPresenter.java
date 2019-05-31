package ru.voronezhtsev.weatherapp.view;

import android.util.Log;

import ru.voronezhtsev.weatherapp.db.ForecastsRepository;
import ru.voronezhtsev.weatherapp.db.WeatherRepository;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter {

    private MainView mMainView;
    private WeatherRepository mWeatherRepository;
    private ForecastsRepository mForecastsRepository;
    private static final String TAG = "MainPresenter";


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
                    Log.d(TAG, "Error while getting current weather", throwable);
                });
        mForecastsRepository.getForecast()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(forecastsResponse -> {
                    if (forecastsResponse.getForecast().size() >= 7) {
                        mMainView.showForecast(forecastsResponse.getForecast().subList(0, 7));
                    } else {
                        mMainView.showForecast(forecastsResponse.getForecast());
                    }
                }, error -> {
                });
    }

    void onDetachView() {

    }
}
