package ru.voronezhtsev.weatherapp.net.models.forecast;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Ответ со списком погоды от сервиса openweathermap.org
 *
 * @author Воронежцев Игорь on 10.01.2019
 */
public class ForecastsResponse {

    @SerializedName("cod")
    private String cod;
    @SerializedName("message")
    private Double message;
    @SerializedName("cnt")
    private Integer cnt;
    @SerializedName("list")
    private List<Forecast> list = null;
    @SerializedName("city")
    private City city;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public List<Forecast> getForecast() {
        return list;
    }

    public void setForecast(List<Forecast> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
