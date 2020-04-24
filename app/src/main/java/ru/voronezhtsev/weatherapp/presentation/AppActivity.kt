package ru.voronezhtsev.weatherapp.presentation

import android.Manifest
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import ru.voronezhtsev.weatherapp.App.Companion.component
import ru.voronezhtsev.weatherapp.R
import ru.voronezhtsev.weatherapp.models.presentation.CityModel
import ru.voronezhtsev.weatherapp.models.presentation.MainScreenModel
import ru.voronezhtsev.weatherapp.models.presentation.WeatherModel

class AppActivity : AppCompatActivity(), AddPlaceFragment.OnAddPlaceClickListener {

    companion object {
        private const val REQUEST_COARSE_LOCATION: Int = 1
        private const val WEATHER_LIST_FRAGMENT = "WEATHER_LIST_FRAGMENT"
    }

    internal lateinit var viewModel: MainScreenViewModel
    lateinit var cities: List<CityModel>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), REQUEST_COARSE_LOCATION)
        addPlaceButton.setOnClickListener { addPlace() }
        viewModel = ViewModelProviders.of(this, component.mainScreenViewModelFactory).get(MainScreenViewModel::class.java)
        viewModel.mainScreenModelLiveData.observe(this, Observer {
            showWeather(it)
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadWeather()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is AddPlaceFragment) {
            fragment.setOnAddPlaceClickListener(this)
        }
    }

    private fun addPlace() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(container.id, AddPlaceFragment.newInstance(cities))
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun showWeather(mainScreenModel: MainScreenModel) {
        cities = mainScreenModel.cityList
        // Решается проблема с переворотом экрана, иницициализируется переменная cities для работы
        // кнопки добавить город но лишний раз не добавляется фрагмент с погодой
        if (supportFragmentManager.findFragmentByTag(WEATHER_LIST_FRAGMENT) == null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(container.id,
                    WeatherListFragment.newInstance(mainScreenModel.weatherList, mainScreenModel.cityList), WEATHER_LIST_FRAGMENT);
            fragmentTransaction.commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onAddPlace(city: Long) {
        val fragment = supportFragmentManager.findFragmentByTag(WEATHER_LIST_FRAGMENT) as WeatherListFragment
        fragment.updateWeather(city)
    }
}