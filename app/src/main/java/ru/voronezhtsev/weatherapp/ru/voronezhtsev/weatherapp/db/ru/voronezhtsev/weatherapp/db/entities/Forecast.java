package ru.voronezhtsev.weatherapp.ru.voronezhtsev.weatherapp.db.ru.voronezhtsev.weatherapp.db.entities;

/**
 * Прогноз погоды, который лежит в БД
 *
 * @author Воронежцев Игорь on 16.12.2018.
 */
public class Forecast {
    private long mDateTime;
    private double mTemp;
    private double mTempMin;
    private double mTempMax;

    public long getDateTime() {
        return mDateTime;
    }

    public void setDateTime(long mDateTime) {
        this.mDateTime = mDateTime;
    }

    public double getTemp() {
        return mTemp;
    }

    public void setTemp(double mTemp) {
        this.mTemp = mTemp;
    }

    public double getTempMin() {
        return mTempMin;
    }

    public void setTempMin(double mTempMin) {
        this.mTempMin = mTempMin;
    }

    public double getTempMax() {
        return mTempMax;
    }

    public void setTempMax(double mTempMax) {
        this.mTempMax = mTempMax;
    }
}
