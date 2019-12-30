package ru.voronezhtsev.weatherapp.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.voronezhtsev.weatherapp.models.data.network.WeatherResponse

interface WeatherService {
    @GET("weather")
    fun getWeather(@Query("lat") latitude: String?, @Query("lon") longtitude: String?, @Query("appid") appid: String?): Single<WeatherResponse?>?

    @GET("weather")
    fun getWeather(@Query("id") latitude: String?, @Query("appid") appid: String?): Single<WeatherResponse?>?
}