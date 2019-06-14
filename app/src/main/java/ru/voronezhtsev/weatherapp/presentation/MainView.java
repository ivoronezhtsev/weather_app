package ru.voronezhtsev.weatherapp.presentation;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.voronezhtsev.weatherapp.models.data.network.forecast.Forecast;

public interface MainView extends MvpView {

    void showTemperature(double temp);

    void showForecast(List<Forecast> forecast);
}
