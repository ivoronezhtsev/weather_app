package ru.voronezhtsev.weatherapp.models.presentation

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ForecastModel(val dateTime: String,
                         @DrawableRes
                         val icon: Int,
                         val temp: String,
                         val description: String) : Parcelable