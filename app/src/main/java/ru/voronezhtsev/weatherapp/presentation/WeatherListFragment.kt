package ru.voronezhtsev.weatherapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.weather_list_fragment.*
import ru.voronezhtsev.weatherapp.App.Companion.component
import ru.voronezhtsev.weatherapp.R
import ru.voronezhtsev.weatherapp.models.presentation.CityModel
import ru.voronezhtsev.weatherapp.models.presentation.WeatherModel

private const val ARG_WEATHER = "weather"
private const val ARG_CITIES = "cities"


class WeatherListFragment : Fragment() {
    private var weather: List<WeatherModel>? = null
    private var cities: List<CityModel>? = null
    private lateinit var viewModel: WeatherListViewModel
    private lateinit var weatherAdapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, component.weatherListViewModelFactory).get(WeatherListViewModel::class.java)
        arguments?.let {
            weather = it.getParcelableArrayList(ARG_WEATHER)
            cities = it.getParcelableArrayList(ARG_CITIES)
        }
        weatherAdapter = WeatherAdapter(emptyList<WeatherModel>().toMutableList(), View.OnClickListener { viewModel.loadWeather(getCityId((it.tag as AppCompatTextView).text.toString())) });
        viewModel.weatheListLiveData.observe(this, Observer {
            if (it.isNotEmpty()) {
                noPlaceTextHolder.visibility = GONE
            }
            weatherAdapter.updateData(it)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.weather_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        weatherList.layoutManager = LinearLayoutManager(requireContext())
        weatherList.adapter = weatherAdapter
        super.onViewCreated(view, savedInstanceState)
        if (!weather.isNullOrEmpty()) {
            noPlaceTextHolder.visibility = GONE
            weatherAdapter.updateData(weather!!.toMutableList());
        }
    }

    fun updateWeather(city: Long) = viewModel.loadWeather(city)

    private fun getCityId(city: String) = cities?.find { it.name.equals(city) }!!.id


    companion object {
        @JvmStatic
        fun newInstance(weather: List<WeatherModel>, cities: List<CityModel>) =
                WeatherListFragment().apply {
                    arguments = Bundle().apply {
                        putParcelableArrayList(ARG_WEATHER, ArrayList(weather))
                        putParcelableArrayList(ARG_CITIES, ArrayList(cities))
                    }
                }
    }
}
