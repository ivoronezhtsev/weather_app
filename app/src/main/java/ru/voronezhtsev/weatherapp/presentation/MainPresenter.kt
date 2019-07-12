package ru.voronezhtsev.weatherapp.presentation

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.voronezhtsev.weatherapp.data.repositories.ForecastsRepository
import ru.voronezhtsev.weatherapp.domain.WeatherInteractor

@InjectViewState
class MainPresenter(private val weatherInteractor: WeatherInteractor, private val forecastsRepository: ForecastsRepository) : MvpPresenter<MainView>() {
    private lateinit var weatherDisposable: Disposable
    private lateinit var forecastDisposable: Disposable

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        weatherDisposable = weatherInteractor.weather
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response -> viewState.showWeather(response) },
                        {   //todo Отобразить алерт о том что не удалось получить данные с сервера
                            throwable ->
                            Log.d(TAG, "Error while getting current weatherInfo", throwable)
                        });
        forecastDisposable = forecastsRepository.forecast
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ forecastsResponse ->
                    if (forecastsResponse.forecast.size >= 7) {
                        viewState.showForecast(forecastsResponse.forecast.subList(0, 7))
                    } else {
                        viewState.showForecast(forecastsResponse.forecast)
                    }
                }, { error -> });
        // лямбды в котлин, диспозебл в презентере
    }

    override fun onDestroy() {
        super.onDestroy()
        weatherDisposable.dispose()
        forecastDisposable.dispose()
    }

    companion object {
        private const val TAG = "MainPresenter" // можно ли без const
    }
}