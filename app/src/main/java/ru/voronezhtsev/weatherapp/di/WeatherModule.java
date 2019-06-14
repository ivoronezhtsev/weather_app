package ru.voronezhtsev.weatherapp.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.voronezhtsev.weatherapp.db.ForecastsDAO;
import ru.voronezhtsev.weatherapp.db.ForecastsRepository;
import ru.voronezhtsev.weatherapp.db.LocationRepository;
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
    @Singleton
    ForecastsRepository provodeForecastsRepository(ForecastsService forecastsService) {
        return new ForecastsRepository(ForecastsDAO.getInstance(mContext), new ResponseConverter(),
                forecastsService);
    }

    @Singleton
    @Provides
    WeatherService provideWeatherService(Retrofit retrofit) {
        return retrofit.create(WeatherService.class);
    }

    @Provides
    @Singleton
    ForecastsService provideForecastsService(Retrofit retrofit) {
        return retrofit.create(ForecastsService.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

    }

    @Provides
    @Singleton
    WeatherRepository provideWeatherRepository(WeatherService weatherService) {
        return new WeatherRepository(weatherService);
    }

    @Provides
    @Singleton
    LocationRepository provideLocationRepository() {
        return new LocationRepository(mContext);
    }
}
