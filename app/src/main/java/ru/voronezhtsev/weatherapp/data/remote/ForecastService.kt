package ru.voronezhtsev.weatherapp.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.voronezhtsev.weatherapp.models.data.network.ForecastResponse

interface ForecastService {

    @GET("forecast")
    fun getForecast(@Query("id") cityId: String, @Query("appid") appId: String): Single<ForecastResponse>

}