package ru.voronezhtsev.weatherapp.presentation

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import ru.voronezhtsev.weatherapp.App.Companion.component
import ru.voronezhtsev.weatherapp.R
import ru.voronezhtsev.weatherapp.models.data.assets.City
import ru.voronezhtsev.weatherapp.models.data.network.Forecast
import ru.voronezhtsev.weatherapp.models.presentation.WeatherModel

class SimpleWeatherActivity : MvpAppCompatActivity(), MainView {

    companion object {
        private const val REQUEST_COARSE_LOCATION: Int = 1
        const val EXTRA_CITY_ID = "CityId"
        @JvmStatic
        fun newIntent(context: Context, cityId: Long): Intent {
            val intent = Intent(context, SimpleWeatherActivity::class.java)
            intent.putExtra(EXTRA_CITY_ID, cityId)
            return intent
        }
    }

    @InjectPresenter
    internal lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter() = MainPresenter(component.weatherInteractor, component.forecastsRepository, intent.getLongExtra(EXTRA_CITY_ID, 0))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecastRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), REQUEST_COARSE_LOCATION)
        val json_string = application.assets.open("city.json").bufferedReader().use {
            it.readText()
        }

        val gson = Gson()
        val cityList = gson.fromJson<Array<City>>(json_string, Array<City>::class.java)
        val map = cityList.map { it.name to it.id }.toMap()
        currentCityInput.setAdapter(ArrayAdapter<Any?>(this,
                android.R.layout.simple_dropdown_item_1line, map.keys.toTypedArray()))
        currentCityInput.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            presenter.update(map.get(parent.getItemAtPosition(position))!!.toLong())
        }
    }

    override fun showForecast(forecast: List<Forecast>) {
        forecastRecycler.adapter = ForecastAdapter(forecast)
    }

    override fun showWeather(weather: WeatherModel) {
        currentTemp.text = weather.temp
        currentCity.text = "${weather.city} (${weather.lon}, ${weather.lat})"
    }
}