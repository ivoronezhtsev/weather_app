package ru.voronezhtsev.weatherapp.data.remote;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.voronezhtsev.weatherapp.models.data.network.WeatherResponse;

public interface WeatherService {
    @GET("weather")
    Single<WeatherResponse> getWeather(@Query("lat") String latitude, @Query("lon") String longtitude, @Query("appid") String appid);
}
