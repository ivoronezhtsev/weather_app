package ru.voronezhtsev.weatherapp;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.voronezhtsev.weatherapp.ru.voronezhtsev.weatherapp.net.api.WeatherService;

public class DownloadService extends IntentService {
    private WeatherService mWeatherService;
    private static final String MOSCOW = "524901";
    private static final String APP_ID = "458a017c6453d7ee6e2cfa3a5ddec547";

    public DownloadService() {
        this("DownloadService");
    }
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DownloadService(String name) {
        super(name);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mWeatherService = retrofit.create(WeatherService.class);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        mWeatherService.getWeather(MOSCOW, APP_ID).execute();
        Intent downloadedIntent = new Intent(MainActivity.ACTION);
        LocalBroadcastManager.getInstance(this).sendBroadcast(downloadedIntent);
    }
}
