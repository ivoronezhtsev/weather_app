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
import ru.voronezhtsev.weatherapp.data.repositories.CityChoicePreferencesRepository;
import ru.voronezhtsev.weatherapp.data.repositories.CityRepository;
import ru.voronezhtsev.weatherapp.data.repositories.DefaultWeatherRepository;
import ru.voronezhtsev.weatherapp.data.repositories.ForecastsRepository;
import ru.voronezhtsev.weatherapp.data.repositories.LocationRepository;
import ru.voronezhtsev.weatherapp.data.repositories.WeatherLocalRepository;
import ru.voronezhtsev.weatherapp.domain.ICityChoiceRepository;
import ru.voronezhtsev.weatherapp.domain.ICityRepository;
import ru.voronezhtsev.weatherapp.domain.ILocationRepository;
import ru.voronezhtsev.weatherapp.domain.IWeatherLocalRepository;
import ru.voronezhtsev.weatherapp.domain.IWeatherRepository;
import ru.voronezhtsev.weatherapp.domain.WeatherInteractor;
import ru.voronezhtsev.weatherapp.presentation.CityChoicePresenter;

@Module
public class WeatherModule {

    @Provides
    @Singleton
    ForecastsRepository provodeForecastsRepository(ForecastsService forecastsService, Context context) {
        return new ForecastsRepository(ForecastsDAO.getInstance(context), new ResponseConverter(),
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
    IWeatherRepository provideWeatherRepository(WeatherService weatherService) {
        return new DefaultWeatherRepository(weatherService);
    }

    @Provides
    @Singleton
    ILocationRepository provideLocationRepository(Context context) {
        return new LocationRepository(context);
    }

    @Singleton
    @Provides
    IWeatherLocalRepository provideWeatherLocalRepository(Context context) {
        return new WeatherLocalRepository(context);
    }

    @Provides
    @Singleton
    WeatherInteractor provideWeatherInteractor(ILocationRepository locationRepository,
                                               IWeatherRepository weatherRepository,
                                               IWeatherLocalRepository weatherLocalRepository) {
        return new WeatherInteractor(weatherRepository, locationRepository, weatherLocalRepository);
    }

    @Singleton
    @Provides
    ICityRepository provideCityRepository(Context context) {
        return new CityRepository(context);
    }

    @Singleton
    @Provides
    MainPresenterFactory provideMainPresenterFactory(WeatherInteractor weatherInteractor, ForecastsRepository forecastsRepository,
                                       ICityRepository cityRepository, IWeatherRepository weatherRepository) {
        return new MainPresenterFactory(weatherInteractor, forecastsRepository, cityRepository, weatherRepository);
    }

    @Singleton
    @Provides
    CityChoicePresenter provideCityChoicePresenter(ICityChoiceRepository cityChoiceRepository,
                                                   ICityRepository cityRepository) {
        return new CityChoicePresenter(cityChoiceRepository, cityRepository);
    }

    @Singleton
    @Provides
    ICityChoiceRepository provideICityChoiceRepository(Context context) {
        return new CityChoicePreferencesRepository(context);
    }
}
