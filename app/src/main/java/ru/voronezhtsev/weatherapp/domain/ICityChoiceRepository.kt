package ru.voronezhtsev.weatherapp.domain

import io.reactivex.Completable
import io.reactivex.Maybe

interface ICityChoiceRepository {
    val city: Maybe<Long>
    fun save(cityId: Long): Completable
}