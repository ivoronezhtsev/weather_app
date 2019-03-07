package ru.voronezhtsev.weatherapp.net.models;

import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    private Double temp;
    @SerializedName("temp_min")
    private Double tempMin;
    @SerializedName("temp_max")
    private Double tempMax;
    @SerializedName("pressure")
    private Double pressure;
    @SerializedName("sea_level")
    private Double seaLevel;
    @SerializedName("grnd_level")
    private Double grndLevel;
    @SerializedName("humidity")
    private Integer humidity;

    /**
     * @return температура в кельвинах
     */
    public Double getTemp() {
        return temp;
    }

    /**
     * @param temp температура в кельвинах
     */
    public void setTemp(Double temp) {
        this.temp = temp;
    }

    /**
     * @return Минимальная температура, это отклонение от {@link Main#getTemp()}
     * присущее большим городам и мегаполисам, в кельвинах, (опционально) //todo Проверить должно быть 0 при отсутствии в ответе
     */
    public Double getTempMin() {
        return tempMin;
    }

    /**
     * @param tempMin Минимальная температура, это отклонение от {@link Main#getTemp()}
     *       присущее большим городам и мегаполисам, в кельвинах, (опционально) //todo Проверить должно быть 0 при отсутствии в ответе
     */
    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    /**
     *
     * @return Максимальная температура, это отклонение от {@link Main#getTemp()},
     *         присущее большим городам и мегаполисам, в кельвинах, (опционально) //todo Проверить должно быть 0 при отсутствии в ответе
     */
    public Double getTempMax() {
        return tempMax;
    }

    /**
     * @param tempMax Максимальная температура, это отклонение от {@link Main#getTemp()},
     *      *         присущее большим городам и мегаполисам, в кельвинах, (опционально) //todo Проверить должно быть 0 при отсутствии в ответе
     */
    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(Double seaLevel) {
        this.seaLevel = seaLevel;
    }

    public Double getGrndLevel() {
        return grndLevel;
    }

    public void setGrndLevel(Double grndLevel) {
        this.grndLevel = grndLevel;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }
}
