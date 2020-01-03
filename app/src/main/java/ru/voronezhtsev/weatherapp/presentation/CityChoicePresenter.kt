package ru.voronezhtsev.weatherapp.presentation

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.voronezhtsev.weatherapp.domain.ICityChoiceRepository
import ru.voronezhtsev.weatherapp.domain.ICityRepository

@InjectViewState
class CityChoicePresenter(private val cityChoiceRepository: ICityChoiceRepository,
                          private val cityRepository: ICityRepository): MvpPresenter<CityChoiceView>() {
    @SuppressLint("CheckResult")
    override fun onFirstViewAttach() {
        cityChoiceRepository.city
                .subscribeOn(Schedulers.io())
                .doOnComplete({})
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({cityId->
                    viewState.runWeatherScreen(cityId)

                }, {/*todo Проверить могут ли быть исключения в Maybe и SharedPreferences*/})
        cityRepository.list
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({list->
                    viewState.showInputCityWidget(list.associateBy({ it.name }, { it.id }))}, {})
    }
    fun save(cityId: Long) {
        cityChoiceRepository.save(cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }
}