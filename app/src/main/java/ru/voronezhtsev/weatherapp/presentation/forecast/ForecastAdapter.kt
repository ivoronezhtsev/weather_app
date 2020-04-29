package ru.voronezhtsev.weatherapp.presentation.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ru.voronezhtsev.weatherapp.R
import ru.voronezhtsev.weatherapp.databinding.ForecastItemBinding
import ru.voronezhtsev.weatherapp.models.presentation.ForecastModel

class ForecastAdapter(private val forecast: List<ForecastModel>) : RecyclerView.Adapter<ForecastHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ForecastItemBinding = DataBindingUtil.inflate(inflater, R.layout.forecast_item, parent, false)
        return ForecastHolder(binding)
    }

    override fun getItemCount(): Int {
        return forecast.size
    }

    override fun onBindViewHolder(holder: ForecastHolder, position: Int) {
        holder.bind(forecast[position])
    }
}

class ForecastHolder(private val binding: ForecastItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(forecast: ForecastModel) {
        binding.forecast = forecast
        binding.executePendingBindings()
    }
}
