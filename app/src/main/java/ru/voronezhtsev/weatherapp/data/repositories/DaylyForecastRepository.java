package ru.voronezhtsev.weatherapp.data.repositories;

import ru.voronezhtsev.weatherapp.db.ForecastsDAO;

/**
 * Репозиторий с
 */
public class DaylyForecastRepository {
    private final ForecastsDAO mForecastsDAO;
    public DaylyForecastRepository(ForecastsDAO forecastsDAO) {
        mForecastsDAO = forecastsDAO;
    }
}
