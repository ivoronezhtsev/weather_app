package ru.voronezhtsev.weatherapp.presentation.forecast

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object DataBindingAdapter {

    @BindingAdapter("weatherIcon")
    @JvmStatic
    fun setImageResource(imageView: ImageView, resource: Int) {
        imageView.setImageResource(resource)
    }

}