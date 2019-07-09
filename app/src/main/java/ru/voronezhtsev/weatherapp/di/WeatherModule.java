package ru.voronezhtsev.weatherapp.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.voronezhtsev.weatherapp.data.db.ForecastsDAO;
import ru.voronezhtsev.weatherapp.data.db.ResponseConverter;
import ru.voronezhtsev.weatherapp.data.remote.ForecastsService;
import ru.voronezhtsev.weatherapp.data.remote.WeatherService;
import ru.voronezhtsev.weatherapp.data.repositories.ForecastsRepository;
import ru.voronezhtsev.weatherapp.data.repositories.LocationRepository;
import ru.voronezhtsev.weatherapp.data.repositories.WeatherLocalRepository;
import ru.voronezhtsev.weatherapp.data.repositories.WeatherRepository;
import ru.voronezhtsev.weatherapp.domain.WeatherInteractor;

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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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

    @Singleton
    @Provides
    WeatherLocalRepository provideWeatherLocalRepository() {
        return new WeatherLocalRepository(mContext);
    }

    @Provides
    @Singleton
    WeatherInteractor provideWeatherInteractor(LocationRepository locationRepository,
                                               WeatherRepository weatherRepository,
                                               WeatherLocalRepository weatherLocalRepository) {
        return new WeatherInteractor(weatherRepository, locationRepository, weatherLocalRepository);
    }
}
