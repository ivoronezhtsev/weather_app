package ru.voronezhtsev.weatherapp.net.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.voronezhtsev.weatherapp.net.models.ForecastsResponse;
import rx.Single;

public interface ForecastsService {
    /**
     * Получить прогноз погоды на 5 дней каждые 3 часа
     *
     * @param cityId ИД города
     * @param appid  ИД приложения
     * @return список, содержащий прогнозы погоды
     */
    @GET("forecast")
    Single<ForecastsResponse> getForecasts(@Query("id") String cityId, @Query("appid") String appid);
}
