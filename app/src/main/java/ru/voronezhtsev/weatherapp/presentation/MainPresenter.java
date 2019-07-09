package ru.voronezhtsev.weatherapp.presentation;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.voronezhtsev.weatherapp.data.repositories.ForecastsRepository;
import ru.voronezhtsev.weatherapp.domain.WeatherInteractor;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private WeatherInteractor mWeatherInteractor;
    private ForecastsRepository mForecastsRepository;
    private Disposable mWeatherDisposable;
    private Disposable mForecastDisposable;

    private static final String TAG = "MainPresenter";

    MainPresenter(WeatherInteractor weatherInteractor, ForecastsRepository forecastsRepository) {
        mWeatherInteractor = weatherInteractor;
        mForecastsRepository = forecastsRepository;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        mWeatherDisposable = mWeatherInteractor.getWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    getViewState().showWeather(response);
                }, throwable -> {
                    Log.d(TAG, "Error while getting current weatherInfo", throwable);
                });
        mForecastDisposable = mForecastsRepository.getForecast()
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        //todo Как работать c Disposable
        mWeatherDisposable.dispose();
        mForecastDisposable.dispose();
    }
}
