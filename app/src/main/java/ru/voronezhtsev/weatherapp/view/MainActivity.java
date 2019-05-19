package ru.voronezhtsev.weatherapp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import ru.voronezhtsev.weatherapp.R;
import ru.voronezhtsev.weatherapp.di.DaggerWeatherComponent;
import ru.voronezhtsev.weatherapp.di.WeatherComponent;
import ru.voronezhtsev.weatherapp.di.WeatherModule;
import ru.voronezhtsev.weatherapp.net.models.forecast.Forecast;


public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter mMainPresenter;
    private TextView mCurrentTemp;
    private RecyclerView mForecastRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCurrentTemp = findViewById(R.id.current_temp);
        WeatherComponent component = DaggerWeatherComponent.builder().weatherModule(new WeatherModule(this)).build();
        mMainPresenter = new MainPresenter(component.getWeatherRepository(), component.getForecastsRepository());
        mMainPresenter.onAttachView(this);

        mForecastRecycler = findViewById(R.id.forecast_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mForecastRecycler.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.onDetachView();
    }

    @Override
    public void showTemperature(double temp) {
        mCurrentTemp.setText(TempUtils.trimZeroes(Math.round(temp)));
    }

    @Override
    public void showForecast(List<Forecast> forecast) {
        mForecastRecycler.setAdapter(new ForecastAdapter(forecast));
    }
}
