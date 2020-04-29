package ru.voronezhtsev.weatherapp.presentation.weatherlist

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.voronezhtsev.weatherapp.domain.WeatherInteractor
import ru.voronezhtsev.weatherapp.models.presentation.WeatherModel
import ru.voronezhtsev.weatherapp.presentation.MainScreenConverter

class WeatherListViewModel(private val weatherInteractor: WeatherInteractor): ViewModel() {

    private val mainScreenConverter: MainScreenConverter = MainScreenConverter()
    val weatheListLiveData: MutableLiveData<List<WeatherModel>> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun loadWeather(city: Long) {
        Single.concat(weatherInteractor.loadFromNetwork(city), weatherInteractor.weatherList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{weatheListLiveData.value = mainScreenConverter.convert(it)}
    }
}