package ru.voronezhtsev.weatherapp.presentation

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.voronezhtsev.weatherapp.domain.ICityRepository
import ru.voronezhtsev.weatherapp.domain.IWeatherRepository
import ru.voronezhtsev.weatherapp.domain.WeatherInteractor
import ru.voronezhtsev.weatherapp.models.presentation.CityModel

@InjectViewState
class MainPresenter(private val cityRepository: ICityRepository,
                    private val weatherRepository: IWeatherRepository,
                    private val mainScreenConverter: MainScreenConverter,
                    private val weatherInteractor: WeatherInteractor,
                    private val city: Long?) : MvpPresenter<MainView>() {
    companion object {
        private const val TAG = "MainPresenter"
    }

    private val isCityModelLoaded = false

    @SuppressLint("CheckResult")
    override fun onFirstViewAttach() {

        //todo Прогрессбар пока погода грузится
        cityRepository.list.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    val cityList = mutableListOf<CityModel>()
                    response.forEach { cityList.add(CityModel(it.id, it.name)) }
                    viewState.showNoPlacesAdded(cityList)
                    weatherInteractor.weatherList
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                viewState.showWeather(mainScreenConverter.convert(it))
                            }, {})
                }, {})
    }

    @SuppressLint("CheckResult")
    fun load(city: Long) {
        viewState.showProgressBar()
        weatherInteractor.getWeather(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { viewState.hideProgressBar() }
                .subscribe({
                    viewState.showWeather(mainScreenConverter.convert(it))
                }, {
                    val a = 1
                })
    }

    fun addPlace() {
        viewState.addPlace()
    }
}