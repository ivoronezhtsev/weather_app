package ru.voronezhtsev.weatherapp.presentation

import android.Manifest
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_main.*
import ru.voronezhtsev.weatherapp.App
import ru.voronezhtsev.weatherapp.R
import ru.voronezhtsev.weatherapp.models.data.network.forecast.Forecast
import ru.voronezhtsev.weatherapp.models.domain.WeatherInfo

class MainActivity : MvpAppCompatActivity(), MainView {

    companion object {
        private const val REQUEST_COARSE_LOCATION: Int = 1
    }

    @InjectPresenter
    internal lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter {
        val component = App.component
        return MainPresenter(component.weatherInteractor, component.forecastsRepository)
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

    override fun showWeather(weatherInfo: WeatherInfo) {
        currentTemp.text = TempUtils.trimZeroes(Math.round(weatherInfo.temperature).toDouble())
        currentCity.text = weatherInfo.city
    }
}