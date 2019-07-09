package ru.voronezhtsev.weatherapp.data.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Objects;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import ru.voronezhtsev.weatherapp.data.WeatherConsts;
import ru.voronezhtsev.weatherapp.domain.IWeatherLocalRepository;
import ru.voronezhtsev.weatherapp.models.domain.WeatherInfo;

public class WeatherLocalRepository extends SQLiteOpenHelper implements IWeatherLocalRepository {
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "weather_info";
    private static final String TEMP_COLUMN = "temperature";
    private static final String CITY_COLUMN = "city";
    private static final String ICON_COLUMN = "icon";
    private static final String CREATE_TABLE =
            "create table if not exists " +
                    TABLE_NAME + "( "
                    + TEMP_COLUMN + " REAL, "
                    + CITY_COLUMN + " TEXT(1024), "
                    + ICON_COLUMN + " TEXT(10))";

    public WeatherLocalRepository(@Nullable Context context) {
        super(context, WeatherConsts.DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public Completable save(WeatherInfo weatherInfo) {
        return Completable.fromAction(() -> {
            SQLiteDatabase database = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(TEMP_COLUMN, weatherInfo.getTemperature());
            cv.put(CITY_COLUMN, weatherInfo.getCity());
            cv.put(ICON_COLUMN, weatherInfo.getIconCode());
            database.insertOrThrow(TABLE_NAME, null, cv);
            database.close();
        });
    }

    public Maybe<WeatherInfo> getWeather() {
        return Maybe.fromCallable(() -> {
            Cursor cursor = null;
            try {
                SQLiteDatabase database = getReadableDatabase();
                cursor = database.query(TABLE_NAME,
                        new String[]{
                                TEMP_COLUMN,
                                CITY_COLUMN,
                                ICON_COLUMN
                        }, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    int tempColIdx = cursor.getColumnIndex(TEMP_COLUMN);
                    int cityColIdx = cursor.getColumnIndex(CITY_COLUMN);
                    int iconColIdx = cursor.getColumnIndex(ICON_COLUMN);
                    return new WeatherInfo(cursor.getDouble(tempColIdx), cursor.getString(cityColIdx),
                            cursor.getString(iconColIdx));
                }
            } finally {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            }
            return null;
        }).filter(Objects::isNull);
    }
}
