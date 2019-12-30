package ru.voronezhtsev.weatherapp.presentation

import android.annotation.SuppressLint
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.voronezhtsev.weatherapp.data.repositories.ForecastsRepository
import ru.voronezhtsev.weatherapp.domain.WeatherInteractor
import ru.voronezhtsev.weatherapp.models.domain.Weather
import ru.voronezhtsev.weatherapp.models.presentation.WeatherModel
import kotlin.math.roundToLong

@InjectViewState
class MainPresenter(private val weatherInteractor: WeatherInteractor,
                    private val forecastsRepository: ForecastsRepository, private val cityId: Long) : MvpPresenter<MainView>() {
    companion object {
        private const val TAG = "MainPresenter"
    }

    @SuppressLint("CheckResult")
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        weatherInteractor.weather(cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    viewState.showWeather(convert(response))
                },
                        {   //todo Отобразить алерт о том что не удалось получить данные с сервера
                            throwable ->
                            Log.d(TAG, "Error while getting current weatherInfo", throwable)
                        });
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
    }

    @SuppressLint("CheckResult")
    fun update(cityId: Long) {
        weatherInteractor.weather(cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response -> viewState.showWeather(convert(response)) },
                        { throwable -> Log.d(TAG, "Error while getting current weatherInfo", throwable) });
    }
    private fun convert(weather: Weather) =
            WeatherModel(weather.temp.roundToLong().toString(), weather.city, weather.lon, weather.lat, null)
}