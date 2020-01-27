package ru.voronezhtsev.weatherapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.voronezhtsev.weatherapp.R
import ru.voronezhtsev.weatherapp.models.presentation.WeatherModel

class WeatherAdapter(private val weather: List<WeatherModel>) : RecyclerView.Adapter<WeatherAdapter.WeatherItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false)
        return WeatherItemHolder(view)
    }

    override fun getItemCount(): Int {
        return weather.size
    }

    override fun onBindViewHolder(holder: WeatherItemHolder, position: Int) {
        holder.bind(weather[position])
    }

    class WeatherItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var weatherIcon: ImageView = itemView.findViewById(R.id.iconId)
        private var city: TextView = itemView.findViewById(R.id.cityName)
        private var currentTemp: TextView = itemView.findViewById(R.id.currentTemp)
        private val loadTime: TextView = itemView.findViewById(R.id.loadTime)


        fun bind(weather: WeatherModel) {
            weatherIcon.setImageDrawable(itemView.context.getDrawable(weather.icon))
            city.text = weather.city
            loadTime.text = weather.dateTime
            currentTemp.text = weather.temp
        }
    }
}