
package ru.voronezhtsev.weatherapp.models.data.network.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

//todo Прочитать нужны ли setter's в Gson
public class WeatherResponse {
    @SerializedName("weatherInfo")
    @Expose
    private List<Weather> weather = null;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("name")
    @Expose
    private String name;

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public String getName() {
        return name;
    }
}
