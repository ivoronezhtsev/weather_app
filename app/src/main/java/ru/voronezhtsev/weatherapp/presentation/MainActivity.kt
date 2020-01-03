package ru.voronezhtsev.weatherapp.presentation

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View.VISIBLE
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_main.*
import ru.voronezhtsev.weatherapp.App.Companion.component
import ru.voronezhtsev.weatherapp.R
import ru.voronezhtsev.weatherapp.models.data.network.Forecast
import ru.voronezhtsev.weatherapp.models.presentation.WeatherModel

class MainActivity : MvpAppCompatActivity(), MainView {

    companion object {
        private const val REQUEST_COARSE_LOCATION: Int = 1
        private const val CITY_ID_PREF = "cityId"
        private const val EXTRA_CITY_ID = "cityId"
        fun newIntent(context: Context, cityId: Long): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(EXTRA_CITY_ID, cityId)
            return intent
        }
    }

    @InjectPresenter
    internal lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter {
        val cityId = intent?.extras?.getLong(EXTRA_CITY_ID)
        if(cityId == 0L || cityId == null) {
            throw IllegalStateException("EXTRA_CITY_ID not defined")
        }
        return component.mainPresenterFactory.get(cityId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecastRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), REQUEST_COARSE_LOCATION)
    }

    override fun showForecast(forecast: List<Forecast>) {
        forecastRecycler.adapter = ForecastAdapter(forecast)
    }

    override fun showInputCity(list: Map<String, Long>) {
        cityInput.visibility = VISIBLE;
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, list.keys.toTypedArray())
        cityInput.setAdapter(adapter);

        cityInput.setOnItemClickListener {
            parent, _, position, _ ->
            val cityId = list[parent.getItemAtPosition(position).toString()]
            presenter.load(cityId!!)
        }
    }

    override fun showWeather(weather: WeatherModel) {
        currentTemp.text = weather.temp
        currentCity.text = weather.city
    }
}