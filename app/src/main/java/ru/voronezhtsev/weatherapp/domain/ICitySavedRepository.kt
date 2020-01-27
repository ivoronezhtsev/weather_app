package ru.voronezhtsev.weatherapp.domain

import io.reactivex.Completable
import io.reactivex.Maybe

/**
 * Репозиторий со списком сохраненных пользователем городов для отображения
 * в приложении
 */
interface ICitySavedRepository {
    /**
     * Список городов для отображения погоды в приложении
     */
    val city: Maybe<List<Long>>
    fun save(cityId: Long): Completable
}