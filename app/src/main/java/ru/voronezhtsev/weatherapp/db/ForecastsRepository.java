package ru.voronezhtsev.weatherapp.db;

import ru.voronezhtsev.weatherapp.net.api.ForecastsService;
import ru.voronezhtsev.weatherapp.net.models.forecast.ForecastsResponse;
import rx.Single;

public class ForecastsRepository {
    private static final String MOSCOW = "524901";
    private static final String APP_ID = "458a017c6453d7ee6e2cfa3a5ddec547";

    private ForecastsDAO mForecastsDAO;
    private ResponseConverter mResponseConverter;
    private ForecastsService mForecastsService;

    public ForecastsRepository(ForecastsDAO forecastsDAO, ResponseConverter responseConverter,
                               ForecastsService forecastsService) {
        mForecastsDAO = forecastsDAO;
        mResponseConverter = responseConverter;
        mForecastsService = forecastsService;
    }

    public void save(ForecastsResponse response) {
        mForecastsDAO.addForecasts(mResponseConverter.convert(response));
    }

    public Single<ForecastsResponse> getForecast() {
        return mForecastsService.getForecasts(MOSCOW, APP_ID);
    }
}
