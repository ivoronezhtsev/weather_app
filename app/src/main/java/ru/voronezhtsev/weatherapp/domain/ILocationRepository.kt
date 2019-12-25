package ru.voronezhtsev.weatherapp.domain

import io.reactivex.Single
import ru.voronezhtsev.weatherapp.models.domain.Location

interface ILocationRepository {
    val location: Single<Location?>
}