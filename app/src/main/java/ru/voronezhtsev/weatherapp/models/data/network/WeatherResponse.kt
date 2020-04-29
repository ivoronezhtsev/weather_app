package ru.voronezhtsev.weatherapp.models.data.network

import com.google.gson.annotations.SerializedName

data class WeatherResponse(@SerializedName("id") val id: Long,
                           @SerializedName("main") val main: Main,
                           @SerializedName("weather") val weather: List<Weather>,
                           @SerializedName("name") val name: String,
                           @SerializedName("dt") val dateTime: Long
)

data class Weather(@SerializedName("id") val id: Int,
                   @SerializedName("icon") val icon: String,
                   @SerializedName("description") val description: String
)