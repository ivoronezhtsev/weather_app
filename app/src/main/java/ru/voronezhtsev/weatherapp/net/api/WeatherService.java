package ru.voronezhtsev.weatherapp.net.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.voronezhtsev.weatherapp.net.models.ForecastsResponse;
import rx.Single;

public interface WeatherService {
    @GET("weather")
    Single<ForecastsResponse> getWeather(@Query("id") String cityId, @Query("appid") String appid);
}
