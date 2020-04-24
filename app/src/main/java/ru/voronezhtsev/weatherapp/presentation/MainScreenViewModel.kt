package ru.voronezhtsev.weatherapp.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.voronezhtsev.weatherapp.domain.ICityRepository
import ru.voronezhtsev.weatherapp.domain.WeatherInteractor
import ru.voronezhtsev.weatherapp.models.presentation.CityModel
import ru.voronezhtsev.weatherapp.models.presentation.MainScreenModel


class MainScreenViewModel(private val cityRepository: ICityRepository,
                          private val mainScreenConverter: MainScreenConverter,
                          private val weatherInteractor: WeatherInteractor) : ViewModel() {

    val mainScreenModelLiveData: MutableLiveData<MainScreenModel> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun loadWeather() {
        //todo Прогрессбар пока погода грузится
        cityRepository.list.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    val cityList = mutableListOf<CityModel>()
                    response.forEach { cityList.add(CityModel(it.id, it.name)) }
                    weatherInteractor.weatherList
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                mainScreenModelLiveData.value = MainScreenModel(mainScreenConverter.convert(it), cityList)
                            }, {})
                }, {})
    }

}