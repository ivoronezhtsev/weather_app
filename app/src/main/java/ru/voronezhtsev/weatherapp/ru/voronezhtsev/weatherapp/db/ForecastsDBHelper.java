package ru.voronezhtsev.weatherapp.ru.voronezhtsev.weatherapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ForecastsDBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String NAME = "forecasts.db";
    private static final String CREATE_TABLE_DDL =
            "create table if not exists forecasts(datetime INTEGER, "
            + "temperature REAL)";

    public ForecastsDBHelper(Context context) {
        this(context, NAME, null, VERSION);
    }

    public ForecastsDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DDL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
