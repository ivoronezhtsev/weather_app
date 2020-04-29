package ru.voronezhtsev.weatherapp.presentation.forecast

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.voronezhtsev.weatherapp.domain.ForecastInteractor
import ru.voronezhtsev.weatherapp.models.presentation.ForecastModel
import ru.voronezhtsev.weatherapp.presentation.MainScreenConverter

class ForecastViewModel(private val forecastInteractor: ForecastInteractor, private val mainScreenConverter: MainScreenConverter) : ViewModel() {
    val forecast: MutableLiveData<List<ForecastModel>> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun load(cityName: String) {
        forecastInteractor.getForecast(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    forecast.value = mainScreenConverter.convertForecast(it)
                }, {
                    Log.e("ForecastViewModel", "error", it)
                })
    }
}