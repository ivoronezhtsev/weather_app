
package ru.voronezhtsev.weatherapp.net.models.forecast;

import com.google.gson.annotations.SerializedName;

public class Clouds {

    @SerializedName("all")
    private Integer all;

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

}
