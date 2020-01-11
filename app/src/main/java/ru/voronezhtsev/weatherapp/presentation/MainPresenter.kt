package ru.voronezhtsev.weatherapp.presentation

import android.annotation.SuppressLint
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.voronezhtsev.weatherapp.data.repositories.ForecastsRepository
import ru.voronezhtsev.weatherapp.domain.ICityRepository
import ru.voronezhtsev.weatherapp.domain.IWeatherRepository
import ru.voronezhtsev.weatherapp.domain.WeatherInteractor

@InjectViewState
class MainPresenter(private val weatherInteractor: WeatherInteractor,
                    private val forecastsRepository: ForecastsRepository,
                    private val cityRepository: ICityRepository,
                    private val weatherRepository: IWeatherRepository,
                    private val mainScreenConverter: MainScreenConverter,
                    private val city: Long?) : MvpPresenter<MainView>() {
    companion object {
        private const val TAG = "MainPresenter"
    }

    @SuppressLint("CheckResult")
    override fun onFirstViewAttach() {
        //todo Прогрессбар пока погода грузится
        weatherInteractor.getWeather(city!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    viewState.showWeather(mainScreenConverter.convert(response))
                },
                        {   //todo Отобразить алерт о том что не удалось получить данные с сервера
                            throwable ->
                            Log.d(TAG, "Error while getting current weatherInfo", throwable)
                        })
        forecastsRepository.forecast
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ forecastsResponse ->
                    if (forecastsResponse?.forecast != null) {
                        if (forecastsResponse.forecast.size >= 7) {
                            viewState.showForecast(forecastsResponse.forecast.subList(0, 7))
                        } else {
                            viewState.showForecast(forecastsResponse.forecast)
                        }
                    }
                }, { error -> })
        cityRepository.list.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->
                    viewState.showInputCity(list.associateBy({ it.name }, { it.id }))
                }, {})
    }

    @SuppressLint("CheckResult")
    fun load(city: Long) {
        weatherRepository.getWeather(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response -> viewState.showWeather(mainScreenConverter.convert(response)) },
                        {})
    }
}