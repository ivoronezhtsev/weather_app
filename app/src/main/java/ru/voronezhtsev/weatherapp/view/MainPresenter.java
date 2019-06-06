package ru.voronezhtsev.weatherapp.view;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.voronezhtsev.weatherapp.db.ForecastsRepository;
import ru.voronezhtsev.weatherapp.db.WeatherRepository;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private WeatherRepository mWeatherRepository;
    private ForecastsRepository mForecastsRepository;
    private static final String TAG = "MainPresenter";


    MainPresenter(WeatherRepository weatherRepository, ForecastsRepository forecastsRepository) {
        mWeatherRepository = weatherRepository;
        mForecastsRepository = forecastsRepository;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        mWeatherRepository.getWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    getViewState().showTemperature(response.getMain().getTemp());
                }, throwable -> {
                    Log.d(TAG, "Error while getting current weather", throwable);
                });
        mForecastsRepository.getForecast()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(forecastsResponse -> {
                    if (forecastsResponse.getForecast().size() >= 7) {
                        getViewState().showForecast(forecastsResponse.getForecast().subList(0, 7));
                    } else {
                        getViewState().showForecast(forecastsResponse.getForecast());
                    }
                }, error -> {
                });
    }
}
