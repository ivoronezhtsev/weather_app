package ru.voronezhtsev.weatherapp.models.domain

/**
 * Модель города из справочника с городами, который предоставил сервис погоды
 *
 * @property id Идентификатор города для запроса у сервиса погоды
 * @property name Название города
 *
 * @author Воронежцев Игорь
 */
data class CityModel(val id: Long, val name: String)