package ru.voronezhtsev.weatherapp.models.presentation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CityModel(val id: Long, val name: String) : Parcelable