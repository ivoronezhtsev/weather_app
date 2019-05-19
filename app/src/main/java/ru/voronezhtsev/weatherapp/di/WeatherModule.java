package ru.voronezhtsev.weatherapp.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.voronezhtsev.weatherapp.db.ForecastsDAO;
import ru.voronezhtsev.weatherapp.db.ForecastsRepository;
import ru.voronezhtsev.weatherapp.db.ResponseConverter;
import ru.voronezhtsev.weatherapp.db.WeatherRepository;
import ru.voronezhtsev.weatherapp.net.api.ForecastsService;
import ru.voronezhtsev.weatherapp.net.api.WeatherService;

@Module
public class WeatherModule {

    private Context mContext;

    public WeatherModule(Context context) {
        mContext = context;
    }

    @Provides
    public ForecastsRepository provodeForecastsRepository(ForecastsService forecastsService) {
        return new ForecastsRepository(ForecastsDAO.getInstance(mContext), new ResponseConverter(),
                forecastsService);
    }

    @Provides
    WeatherService provideWeatherService(Retrofit retrofit) {
        return retrofit.create(WeatherService.class);
    }

    @Provides
    ForecastsService provideForecastsService(Retrofit retrofit) {
        return retrofit.create(ForecastsService.class);
    }

    @Provides
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

    }

    @Provides
    WeatherRepository provideWeatherRepository(WeatherService weatherService) {
        return new WeatherRepository(weatherService);
    }

}
