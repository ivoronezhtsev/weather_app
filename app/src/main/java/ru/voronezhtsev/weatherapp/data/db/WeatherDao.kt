package ru.voronezhtsev.weatherapp.data.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ru.voronezhtsev.weatherapp.di.SingletonHolder
import ru.voronezhtsev.weatherapp.models.data.network.WeatherResponse
import ru.voronezhtsev.weatherapp.models.domain.Weather

class WeatherDao private constructor(context: Context) : SQLiteOpenHelper(context, "weather.db", null, 1) {

    private val DATABASE_DDL = "create table WEATHER( " +
            "CITY_NAME text, " +
            "TEMPERATURE real, " +
            "ICON_CODE text, " +
            "DATETIME integer, " +
            "DESCRIPTION text, " +
            "constraint CITY_ID_CONSTRAINT primary key(CITY_NAME))"

    companion object : SingletonHolder<WeatherDao, Context>(::WeatherDao)

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DATABASE_DDL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun save(response: WeatherResponse) {
        writableDatabase.replaceOrThrow("WEATHER", null, getContentValues(response))
    }

    private fun getContentValues(response: WeatherResponse): ContentValues {
        val contentValues = ContentValues()
        contentValues.put("CITY_NAME", response.name)
        contentValues.put("TEMPERATURE", response.main.temp)
        contentValues.put("ICON_CODE", response.weather[0].icon)
        contentValues.put("DATETIME", response.dt)
        contentValues.put("DESCRIPTION", response.weather[0].description)
        return contentValues
    }

    fun load(): List<Weather> {
        val cursor = readableDatabase.query("WEATHER", null, null, null, null, null, "CITY_NAME")
        val weatherList = mutableListOf<Weather>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val cityName = cursor.getString(cursor.getColumnIndex("CITY_NAME"));
                val temperature = cursor.getDouble(cursor.getColumnIndex("TEMPERATURE"));
                val iconCode = cursor.getString(cursor.getColumnIndex("ICON_CODE"));
                val dateTime = cursor.getLong(cursor.getColumnIndex("DATETIME"))
                val description = cursor.getString(cursor.getColumnIndex("DESCRIPTION"))
                val weather = Weather(temperature, cityName, iconCode, dateTime, description);
                weatherList.add(weather)
                cursor.moveToNext()
            }
        }
        cursor.close()
        return weatherList
    }
}