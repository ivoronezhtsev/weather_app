package ru.voronezhtsev.weatherapp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.voronezhtsev.weatherapp.net.models.forecast.Forecast;

public interface MainView extends MvpView {

    void showTemperature(double temp);

    void showForecast(List<Forecast> forecast);
}
