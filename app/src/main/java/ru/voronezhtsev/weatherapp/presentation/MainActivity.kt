package ru.voronezhtsev.weatherapp.presentation

import android.Manifest
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_no_place.*
import ru.voronezhtsev.weatherapp.App.Companion.component
import ru.voronezhtsev.weatherapp.R
import ru.voronezhtsev.weatherapp.models.presentation.CityModel
import ru.voronezhtsev.weatherapp.models.presentation.WeatherModel

class MainActivity : MvpAppCompatActivity(), MainView, OnPlaceClickListener {

    companion object {
        private const val REQUEST_COARSE_LOCATION: Int = 1
    }
    @InjectPresenter
    internal lateinit var presenter: MainPresenter
    lateinit var cities: List<CityModel>;

    @ProvidePresenter
    fun providePresenter(): MainPresenter = component.mainPresenterFactory.get(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), REQUEST_COARSE_LOCATION)
        add_place_button.setOnClickListener { v: View -> presenter.addPlace() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun showNoPlacesAdded(cityList: List<CityModel>) {

        cities = cityList
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(container.id, NoPlaceFragment.newInstance("", ""));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit()
    }

    override fun addPlace() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(container.id, AddPlaceFragment.newInstance(cities))
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun showWeather(weather: List<WeatherModel>) {
        noPlaceTextHolder.visibility = GONE
        weatherList.layoutManager = LinearLayoutManager(this)
        weatherList.adapter = WeatherAdapter(weather)
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