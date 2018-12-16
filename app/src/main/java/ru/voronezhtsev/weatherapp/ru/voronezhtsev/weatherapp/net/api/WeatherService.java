package ru.voronezhtsev.weatherapp.ru.voronezhtsev.weatherapp.net.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.voronezhtsev.weatherapp.ru.voronezhtsev.weatherapp.net.models.Response;

public interface WeatherService {
    @GET("forecast")
    Call<Response> getWeather(@Query("id") String cityId, @Query("appid") String appid);

}
