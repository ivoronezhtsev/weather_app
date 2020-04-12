package ru.voronezhtsev.weatherapp.presentation

import android.Manifest
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.widget.AppCompatTextView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.weather_list_fragment.*
import ru.voronezhtsev.weatherapp.App.Companion.component
import ru.voronezhtsev.weatherapp.R
import ru.voronezhtsev.weatherapp.models.presentation.CityModel
import ru.voronezhtsev.weatherapp.models.presentation.WeatherModel

class AppActivity : MvpAppCompatActivity(), MainView, OnPlaceClickListener {

    companion object {
        private const val REQUEST_COARSE_LOCATION: Int = 1
        private const val SHOW_PROGRESS_DELAY_MS = 500L;
    }

    @InjectPresenter
    internal lateinit var presenter: MainPresenter
    lateinit var cities: List<CityModel>;
    private val showProgressBarRunnable = Runnable {
        progressBar.visibility = VISIBLE
        noPlaceTextHolder.visibility = GONE
        weatherList.visibility = GONE
    }
    private val handler = Handler()
    private lateinit var weatherAdapter: WeatherAdapter
    @ProvidePresenter
    fun providePresenter(): MainPresenter = component.mainPresenterFactory.get(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), REQUEST_COARSE_LOCATION)
        addPlaceButton.setOnClickListener { presenter.addPlace() }
        weatherAdapter = WeatherAdapter(mutableListOf(), View.OnClickListener {
            val cityName = (it.tag as AppCompatTextView).text
            val selectedCity = cities.find { it.name == cityName }
            presenter.load(selectedCity!!.id)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun showNoPlacesAdded(cityList: List<CityModel>) {
        cities = cityList
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(container.id, WeatherListFragment.newInstance("", ""), "NO_PLACE_FRAGMENT");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit()
        supportFragmentManager.executePendingTransactions()
    }

    override fun addPlace() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(container.id, AddPlaceFragment.newInstance(cities), "ADD_PLACE_FRAGMENT")
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun showWeather(weather: List<WeatherModel>) {
        if (weather.isNotEmpty()) {
            noPlaceTextHolder.visibility = GONE
            weatherAdapter.updateData(weather)
        }
    }

    override fun showProgressBar() {
        handler.postDelayed(showProgressBarRunnable, SHOW_PROGRESS_DELAY_MS)
    }

    override fun hideProgressBar() {
        handler.removeCallbacks(showProgressBarRunnable)
        progressBar.visibility = GONE
        weatherList.visibility = VISIBLE
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(cityId: Long) {
        presenter.load(cityId)
    }
}