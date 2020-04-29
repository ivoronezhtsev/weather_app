package ru.voronezhtsev.weatherapp.models.data.network

import com.google.gson.annotations.SerializedName

data class Main(@SerializedName("temp") val temp: Double)
