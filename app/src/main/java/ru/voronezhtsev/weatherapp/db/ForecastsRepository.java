package ru.voronezhtsev.weatherapp.db;

import ru.voronezhtsev.weatherapp.net.models.ForecastsResponse;

public class ForecastsRepository {
    private ForecastsDAO mForecastsDAO;
    private ResponseConverter mResponseConverter;

    public ForecastsRepository(ForecastsDAO forecastsDAO, ResponseConverter responseConverter) {
        mForecastsDAO = forecastsDAO;
        mResponseConverter = responseConverter;
    }

    public void save(ForecastsResponse response) {
        mForecastsDAO.addForecasts(mResponseConverter.convert(response));
    }
}
