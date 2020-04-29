package ru.voronezhtsev.weatherapp.data.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ru.voronezhtsev.weatherapp.di.SingletonHolder
import ru.voronezhtsev.weatherapp.models.data.network.ForecastResponse
import ru.voronezhtsev.weatherapp.models.domain.Forecast

class ForecastsDAO private constructor(context: Context) : SQLiteOpenHelper(context, "forecasts.db", null, 1) {
    //todo На экране выводится прогноз на текущие 24 часа, а хранится на 5 дней для каждого города
    private val DATABASE_DDL = "create table if not exists FORECASTS( " +
            "CITY_NAME   text, " +
            "TEMPERATURE real, " +
            "ICON_CODE   text, " +
            "DATETIME    integer, " +
            "DESCRIPTION text)"

    companion object : SingletonHolder<ForecastsDAO, Context>(::ForecastsDAO)

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DATABASE_DDL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun save(response: ForecastResponse) {
        for (contentValues in getContentValues(response)) {
            writableDatabase.replaceOrThrow("FORECASTS", null, contentValues)
        }
    }

    private fun getContentValues(response: ForecastResponse): List<ContentValues> {
        val result = mutableListOf<ContentValues>()
        for (forecast in response.forecast) {
            val contentValues = ContentValues()
            contentValues.put("CITY_NAME", response.city.name)
            contentValues.put("TEMPERATURE", forecast.main.temp - 273.15)
            contentValues.put("ICON_CODE", forecast.weather[0].icon)
            contentValues.put("DATETIME", forecast.dateTime)
            contentValues.put("DESCRIPTION", forecast.weather[0].description)
            result.add(contentValues)
        }
        return result
    }

    fun load(cityName: String): List<Forecast> {
        val cursor = readableDatabase.query("FORECASTS", null, "CITY_NAME = ?",
                arrayOf(cityName), null, null, "DATETIME")
        val result = mutableListOf<Forecast>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val cityName = cursor.getString(cursor.getColumnIndex("CITY_NAME"));
                val temperature = cursor.getDouble(cursor.getColumnIndex("TEMPERATURE"));
                val iconCode = cursor.getString(cursor.getColumnIndex("ICON_CODE"));
                val dateTime = cursor.getLong(cursor.getColumnIndex("DATETIME"))
                val description = cursor.getString(cursor.getColumnIndex("DESCRIPTION"))
                val forecast = Forecast(cityName, dateTime, temperature, iconCode, description)
                result.add(forecast)
                cursor.moveToNext()
            }
        }
        cursor.close()
        return result
    }
}