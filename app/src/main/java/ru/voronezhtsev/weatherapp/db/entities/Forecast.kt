package ru.voronezhtsev.weatherapp.db.entities

/**
 * Сущность прогноза погоды для хранения в БД
 *
 * @author Воронежцев Игорь on 12.01.19
 */
data class Forecast(
        /**
         * Время UNIX, UTC, мс
         */
        val dateTime: Long,
        /**
         * Температура в кельвинах
         */
        val temp: Double,
        /**
         * Минимальная температура, это отклонение от температуры, присущее большим городам
         * и мегаполисам
         */
        val tempMin: Double,
        /**
         * Максимальная температура, это отклонение от температуры, присущее большим городам
         * и мегаполисам
         */
        val tempMax: Double
)
