package ru.voronezhtsev.weatherapp.presentation.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.forecast_fragment.*
import ru.voronezhtsev.weatherapp.App.Companion.component
import ru.voronezhtsev.weatherapp.R

private const val ARG_CITY = "ARG_CITY"

class ForecastFragment : Fragment() {

    private lateinit var viewModel: ForecastViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, component.forecastViewModelFactory).get(ForecastViewModel::class.java)
        viewModel.forecast.observe(this, Observer {
            forecastRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            forecastRecyclerView.adapter = ForecastAdapter(it)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.forecast_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cityName = arguments?.getString(ARG_CITY)
        if (cityName != null) {
            viewModel.load(cityName)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(city: String) = ForecastFragment().apply {
            arguments = Bundle().apply { putString(ARG_CITY, city) }
        }
    }
}