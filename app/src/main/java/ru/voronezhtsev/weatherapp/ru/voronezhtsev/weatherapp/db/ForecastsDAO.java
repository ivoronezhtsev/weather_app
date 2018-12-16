package ru.voronezhtsev.weatherapp.ru.voronezhtsev.weatherapp.db;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import ru.voronezhtsev.weatherapp.ru.voronezhtsev.weatherapp.db.ru.voronezhtsev.weatherapp.db.entities.Forecast;

public class ForecastsDAO {

    private static final String TAG = "ForecastsDAO";
    private static final String ERR_ADD_FORECAST = "Error while adding forecast with datetime: ";
    private ForecastsDBHelper mForecastsDBHelper;

    public ForecastsDAO(ForecastsDBHelper forecastsDBHelper) {
        mForecastsDBHelper = forecastsDBHelper;
    }

    public void addForecast(Forecast forecast) {
        try (SQLiteDatabase database = mForecastsDBHelper.getWritableDatabase()) {
            ContentValues
        } catch (SQLException e) {
            Log.e(TAG, ERR_ADD_FORECAST + forecast.getDateTime(), e);
        }
    }
}
