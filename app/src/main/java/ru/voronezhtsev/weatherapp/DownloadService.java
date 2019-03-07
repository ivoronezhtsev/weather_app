package ru.voronezhtsev.weatherapp;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.voronezhtsev.weatherapp.db.ForecastsDAO;
import ru.voronezhtsev.weatherapp.net.WeatherResponseConverter;
import ru.voronezhtsev.weatherapp.net.api.WeatherService;
import ru.voronezhtsev.weatherapp.net.models.WeatherResponse;

public class DownloadService extends IntentService {
    private static final String TAG = "DownloadService";
    private WeatherService mWeatherService;
    private static final String MOSCOW = "524901";
    private static final String APP_ID = "458a017c6453d7ee6e2cfa3a5ddec547";
    private ForecastsDAO mForecastsDAO = ForecastsDAO.getInstance(this);

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
        try {
            Response<WeatherResponse> response = mWeatherService.getWeather(MOSCOW, APP_ID).execute();
            if (response.isSuccessful() && response.body() != null) {
                Intent downloadedIntent = new Intent(MainActivity.FORECAST_DOWNLOADED);
                mForecastsDAO.addForecasts(WeatherResponseConverter.convert(response.body()));
                LocalBroadcastManager.getInstance(this).sendBroadcast(downloadedIntent);
            } else {
                throw new IOException(response.message());
            }
        } catch (IOException e) {
            Intent errorIntent = new Intent(MainActivity.FORECAST_DOWNLOAD_FAILS);
            LocalBroadcastManager.getInstance(this).sendBroadcast(errorIntent);
        }
    }
}
