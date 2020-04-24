package ru.voronezhtsev.weatherapp.models.presentation

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherModel(
        val temp: String,
        val city: String,
        val dateTime: String,
        @DrawableRes
        val icon: Int,
        val description: String
) : Parcelable