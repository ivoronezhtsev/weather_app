package ru.voronezhtsev.weatherapp.domain
import io.reactivex.Single
import ru.voronezhtsev.weatherapp.models.domain.CityModel

interface ICityRepository {
    val list: Single<Array<CityModel>>
}