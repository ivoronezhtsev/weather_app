package ru.voronezhtsev.weatherapp.view;

import java.util.List;

import ru.voronezhtsev.weatherapp.net.models.forecast.Forecast;

public interface MainView {

    void showTemperature(double temp);

    void showForecast(List<Forecast> forecast);
}
