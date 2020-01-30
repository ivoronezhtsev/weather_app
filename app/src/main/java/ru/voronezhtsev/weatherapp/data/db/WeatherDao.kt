package ru.voronezhtsev.weatherapp.data.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ru.voronezhtsev.weatherapp.models.data.network.WeatherResponse

class WeatherDao private constructor(val context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object {
        private const val NAME: String = "weather.db"
        private const val VERSION = 1
        private const val DATABASE_DDL = "create table WEATHER( " +
                "CITY_ID integer, " +
                "TEMP real, " +
                "ICON text, " +
                "DT integer, " +
                "constraint CITY_ID_CONSTRAINT primary key(CITY_ID))"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val ddl = context.assets.open(DATABASE_DDL).bufferedReader().use { it.readText() }
        db.execSQL(ddl)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun save(response: WeatherResponse) {
        writableDatabase.insertOrThrow("WEATHER", null, getContentValues(response))
    }

    private fun getContentValues(response: WeatherResponse): ContentValues {
        val contentValues = ContentValues()
        contentValues.put("city_id", response.weather[0].id)
        contentValues.put("temp", response.main.temp)
        contentValues.put("icon", response.weather[0].icon)
        contentValues.put("dt", response.dt)
        return contentValues
    }
}