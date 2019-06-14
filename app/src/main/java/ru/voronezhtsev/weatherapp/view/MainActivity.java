package ru.voronezhtsev.weatherapp.view;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import ru.voronezhtsev.weatherapp.App;
import ru.voronezhtsev.weatherapp.R;
import ru.voronezhtsev.weatherapp.di.WeatherComponent;
import ru.voronezhtsev.weatherapp.net.models.forecast.Forecast;


public class MainActivity extends MvpAppCompatActivity implements MainView {

    private static final int REQUEST_FINE_LOCATION = 1;
    @InjectPresenter
    MainPresenter mMainPresenter;
    private TextView mCurrentTemp;
    private RecyclerView mForecastRecycler;

    @ProvidePresenter
    MainPresenter providePresenter() {
        WeatherComponent component = App.getInstance().getWeatherComponent();
        return new MainPresenter(component.getWeatherRepository(), component.getForecastsRepository(),
                component.getLocationRepository());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCurrentTemp = findViewById(R.id.current_temp);
        mForecastRecycler = findViewById(R.id.forecast_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mForecastRecycler.setLayoutManager(layoutManager);
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_FINE_LOCATION);
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
