
package ru.voronezhtsev.weatherapp.net.models.forecast;


import com.google.gson.annotations.SerializedName;

/**
 * Единица прогноза погоды по времени
 *
 * @author Воронежцев Игорь on 05.01.2019
 */
public class Forecast {

    @SerializedName("dt")
    private Long dt;
    @SerializedName("main")
    private Main main;
    /*@SerializedName("weather")
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
    private String dtTxt;*/

    /**
     * Вернуть время и дату измерения, unix, UTC
     * @return время и дата измерения, мс, с начала эпохи
     */
    public Long getDt() {
        return dt;
    }

    /**
     * Задать время и дату измерения, unix, UTC
     * @param dt время и дата измерения, мс с начала эпохи
     */
    public void setDt(Long dt) {
        this.dt = dt;
    }

    /**
     * Вернуть основной блок прогноза {@link Main}
     * @return основной блок прогноза
     */
    public Main getMain() {
        return main;
    }

    /**
     * Задать основной блок прогноза {@link Main}
     * @param main основной блок прогноза
     */
    public void setMain(Main main) {
        this.main = main;
    }

    /*public List<Weather> getWeather() {
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
    }*/
}
