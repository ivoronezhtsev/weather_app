package ru.voronezhtsev.weatherapp.models.data.network

import com.google.gson.annotations.SerializedName

data class ForecastResponse(@SerializedName("cod") val cod: String,
                            @SerializedName("message") val message: Double,
                            @SerializedName("cnt") val cnt: Int,
                            @SerializedName("list") val forecast: List<Forecast>,
                            @SerializedName("city") val city: City
)

data class Forecast(@SerializedName("dt") val dateTime: Long,
                    @SerializedName("main") val main: Main,
                    @SerializedName("weather") val weather: List<WeatherBlock>
)

data class City(@SerializedName("id") val id: Int,
                @SerializedName("name") val name: String
)

data class WeatherBlock(@SerializedName("main") val main: String,
                        @SerializedName("description") val description: String,
                        @SerializedName("icon") val icon: String
)
