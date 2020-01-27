package ru.voronezhtsev.weatherapp.presentation

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.voronezhtsev.weatherapp.data.repositories.ForecastsRepository
import ru.voronezhtsev.weatherapp.domain.ICityRepository
import ru.voronezhtsev.weatherapp.domain.IWeatherRepository
import ru.voronezhtsev.weatherapp.domain.WeatherInteractor
import ru.voronezhtsev.weatherapp.models.presentation.CityModel
import ru.voronezhtsev.weatherapp.models.presentation.WeatherModel

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
        cityRepository.list.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    val cityList = mutableListOf<CityModel>()
                    response.forEach { city -> cityList.add(CityModel(city.id, city.name)) }
                    viewState.showNoPlacesAdded(cityList)
                }
        /*weatherInteractor.getWeather(city!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    viewState.showWeather(mainScreenConverter.convert(response))
                },
                        {   //todo Отобразить алерт о том что не удалось получить данные с сервера
                            throwable ->
                            Log.d(TAG, "Error while getting current weatherInfo", throwable)
                        })*/
    }

    @SuppressLint("CheckResult")
    fun load(city: Long) {
        weatherRepository.getWeather(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val weather = mutableListOf<WeatherModel>()
                    weather.add(mainScreenConverter.convert(it))
                    viewState.showWeather(weather)
                }, {})
    }

    fun addPlace() {
        viewState.addPlace()
    }
}