
package ru.voronezhtsev.weatherapp.ru.voronezhtsev.weatherapp.net.models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Forecast {

    @SerializedName("dt")
    private Integer dt;
    @SerializedName("main")
    private Main main;
    @SerializedName("weather")
    private List<Weather> weather = null;
    @SerializedName("clouds")
    private Clouds clouds;
    @SerializedName("wind")
    private Wind wind;
    @SerializedName("snow")
    private Snow snow;
    @SerializedName("sys")
    private Sys sys;
    @SerializedName("dt_txt")
    private String dtTxt;

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Snow getSnow() {
        return snow;
    }

    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }
}
