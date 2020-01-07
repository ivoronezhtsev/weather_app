package ru.voronezhtsev.weatherapp.presentation

import android.os.Bundle
import android.view.View.VISIBLE
import android.widget.ArrayAdapter
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_main.*
import ru.voronezhtsev.weatherapp.App.Companion.component
import ru.voronezhtsev.weatherapp.R

class CityChoiceActivity : MvpAppCompatActivity(), CityChoiceView {

    @InjectPresenter
    internal lateinit var presenter: CityChoicePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_choice)
    }

    override fun showInputCityWidget(list: Map<String, Long>) {
        cityInput.visibility = VISIBLE;
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, list.keys.toTypedArray())
        cityInput.setAdapter(adapter);

        cityInput.setOnItemClickListener { parent, _, position, _ ->
            val cityId = list.getValue(parent.getItemAtPosition(position).toString())
            presenter.save(cityId)
            startActivity(MainActivity.newIntent(this, cityId))
            finish()
        }
    }

    override fun runWeatherScreen(cityId: Long) {
        finish()
        startActivity(MainActivity.newIntent(this, cityId))
    }

    @ProvidePresenter
    fun providePresenter() = component.cityChoicePresenter
}
