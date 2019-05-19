package ru.voronezhtsev.weatherapp;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import ru.voronezhtsev.weatherapp.db.ForecastsDAO;
import ru.voronezhtsev.weatherapp.db.ForecastsRepository;

public class DownloadService extends IntentService {
    private static final String TAG = "DownloadService";
    //private WeatherService mWeatherService;
    private static final String MOSCOW = "524901";
    private static final String APP_ID = "458a017c6453d7ee6e2cfa3a5ddec547";
    private ForecastsDAO mForecastsDAO = ForecastsDAO.getInstance(this);
    private ForecastsRepository mForecastsRepository;

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
        //WeatherComponent component = DaggerWeatherComponent.builder().weatherModule(new WeatherModule(this)).build();
        //mWeatherService = component.getWeatherService();
        //mForecastsRepository = component.getForecastsRepository();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        /*try {
            Response<WeatherResponse> response = mWeatherService.getWeather(MOSCOW, APP_ID).execute();
            if (response.isSuccessful() && response.body() != null) {
                //Intent downloadedIntent = new Intent(MainActivity.FORECAST_DOWNLOADED);
                mForecastsRepository.save(response.body());
                //mForecastsDAO.addForecasts(ResponseConverter.convert(response.body()));
                //LocalBroadcastManager.getInstance(this).sendBroadcast(downloadedIntent);
            } else {
                throw new IOException(response.message());
            }
        } catch (IOException e) {
            //Intent errorIntent = new Intent(MainActivity.FORECAST_DOWNLOAD_FAILS);
            LocalBroadcastManager.getInstance(this).sendBroadcast(errorIntent);
        }*/
    }
}
