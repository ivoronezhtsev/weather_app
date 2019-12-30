package ru.voronezhtsev.weatherapp.domain

import io.reactivex.Single
import ru.voronezhtsev.weatherapp.models.domain.City

interface ICityRepository {
    val list: Single<Array<City>>
}