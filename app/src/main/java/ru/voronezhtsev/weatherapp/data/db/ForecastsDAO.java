package ru.voronezhtsev.weatherapp.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ru.voronezhtsev.weatherapp.models.data.db.Forecast;

public class ForecastsDAO extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "forecasts";
    private static final String DATETIME_COLUMN = "datetime";
    private static final String TEMP_COLUMN = "temperature";
    private static final String TEMP_MIN_COLUMN = "temperature_min";
    private static final String TEMP_MAX_COLUMN = "temperature_max";
    private static final String TAG = "ForecastsDAO";
    private static final String ERR_ADD_FORECAST = "Error while adding forecast with datetime: ";
    private static final int VERSION = 1;
    private static final String NAME = "forecasts.db";

    private static ForecastsDAO INSTANCE = null;
    //todo Растет БД с каждым запуском приложения
    private static final String CREATE_TABLE =
            "create table if not exists " +
                    TABLE_NAME + "("
                    + DATETIME_COLUMN  + " INTEGER, "
                    + TEMP_COLUMN +" REAL, "
                    + TEMP_MIN_COLUMN + " REAL, "
                    + TEMP_MAX_COLUMN+ " REAL)";
    private ForecastsDAO(Context context) {
        super(context, NAME, null, VERSION);
    }

    public static synchronized ForecastsDAO getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new ForecastsDAO(context);
        }
        return INSTANCE;
    }
    public void addForecasts(List<Forecast> forecasts) {
        SQLiteDatabase database = getWritableDatabase();
        for(Forecast forecast: forecasts) {
            ContentValues cv = getContentValues(forecast);
            database.insertOrThrow(TABLE_NAME, null, cv);
        }
        database.close();
    }

    public List<Forecast> getForecasts() {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME,
                new String[] {
                        DATETIME_COLUMN,
                        TEMP_COLUMN,
                        TEMP_MAX_COLUMN,
                        TEMP_MIN_COLUMN
                }, null, null, null, null, null);

        List<Forecast> forecasts = new ArrayList<>();
        if(cursor.moveToFirst()) {
            int dateTimeColIdx = cursor.getColumnIndex(DATETIME_COLUMN);
            int tempColIdx = cursor.getColumnIndex(TEMP_COLUMN);
            int temMinColIdx = cursor.getColumnIndex(TEMP_MIN_COLUMN);
            int tempMaxColIdx = cursor.getColumnIndex(TEMP_MAX_COLUMN);

            while (!cursor.isAfterLast()) {
                long dateTime = cursor.getLong(dateTimeColIdx);
                double temp = cursor.getDouble(tempColIdx);
                double tempMin = cursor.getDouble(temMinColIdx);
                double tempMax = cursor.getDouble(tempMaxColIdx);
                Forecast forecast = new Forecast(dateTime, temp, tempMin, tempMax);
                forecasts.add(forecast);
                cursor.moveToNext();
            }
        }
        return forecasts;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    private ContentValues getContentValues(Forecast forecast) {
        ContentValues cv = new ContentValues();
        cv.put(DATETIME_COLUMN, forecast.getDateTime());
        cv.put(TEMP_COLUMN, forecast.getTemp());
        cv.put(TEMP_MIN_COLUMN, forecast.getTempMin());
        cv.put(TEMP_MAX_COLUMN, forecast.getTempMax());
        return cv;
    }
}