
package ru.voronezhtsev.weatherapp.ru.voronezhtsev.weatherapp.net.models;

import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("speed")
    private Double speed;
    @SerializedName("deg")
    private Integer deg;

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }
}
