package ru.voronezhtsev.weatherapp.net.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.voronezhtsev.weatherapp.net.models.weather.WeatherResponse;
import rx.Single;

public interface WeatherService {
    @GET("weather")
        //todo Погуглить Rxjava с ретрофит м.б. Maybe или вообще без Rx.
    Single<WeatherResponse> getWeather(@Query("id") String cityId, @Query("appid") String appid);
}
