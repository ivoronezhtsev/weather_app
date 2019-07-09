package ru.voronezhtsev.weatherapp.data.remote;

import ru.voronezhtsev.weatherapp.models.data.network.weather.WeatherResponse;
import ru.voronezhtsev.weatherapp.models.domain.WeatherInfo;

public class WeatherResponseConverter {

    public WeatherInfo convert(WeatherResponse response) {
        String iconCode = response.getWeather() != null && !response.getWeather().isEmpty()
                ? response.getWeather().get(0).getIcon() : null;
        return new WeatherInfo(response.getMain().getTemp() - 273.15, response.getName(),
                iconCode);
    }
}
