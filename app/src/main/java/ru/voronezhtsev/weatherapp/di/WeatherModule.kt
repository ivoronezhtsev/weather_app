package ru.voronezhtsev.weatherapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.voronezhtsev.weatherapp.data.db.ForecastsDAO
import ru.voronezhtsev.weatherapp.data.db.WeatherDao
import ru.voronezhtsev.weatherapp.data.remote.ForecastService
import ru.voronezhtsev.weatherapp.data.remote.WeatherService
import ru.voronezhtsev.weatherapp.data.repositories.CityRepository
import ru.voronezhtsev.weatherapp.data.repositories.ForecastsRepositoryImpl
import ru.voronezhtsev.weatherapp.data.repositories.WeatherRepositoryImpl
import ru.voronezhtsev.weatherapp.domain.*
import ru.voronezhtsev.weatherapp.presentation.MainScreenConverter
import ru.voronezhtsev.weatherapp.presentation.MainScreenViewModelFactory
import ru.voronezhtsev.weatherapp.presentation.forecast.ForecastViewModelFactory
import ru.voronezhtsev.weatherapp.presentation.weatherlist.WeatherListViewModelFactory
import javax.inject.Singleton

@Module
class WeatherModule {
    @Provides
    @Singleton
    fun provodeForecastsRepository(forecastService: ForecastService, context: Context): ForecastsRepositoryImpl {
        return ForecastsRepositoryImpl(ForecastsDAO.getInstance(context), forecastService)
    }

    @Singleton
    @Provides
    fun provideWeatherService(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }

    @Provides
    @Singleton
    fun provideForecastsService(retrofit: Retrofit): ForecastService {
        return retrofit.create(ForecastService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherService: WeatherService, context: Context): WeatherRepository {
        return WeatherRepositoryImpl(weatherService, WeatherDao.getInstance(context))
    }

    @Provides
    @Singleton
    fun provideWeatherInteractor(weatherRepository: WeatherRepository,
                                 forecastsRepository: ForecastsRepository): WeatherInteractor {
        return WeatherInteractor(weatherRepository, forecastsRepository)
    }

    @Singleton
    @Provides
    fun provideCityRepository(context: Context): ICityRepository {
        return CityRepository(context)
    }

    @Singleton
    @Provides
    fun privideMainScreenConverter(): MainScreenConverter {
        return MainScreenConverter()
    }

    @Singleton
    @Provides
    fun provideWeatherListViewModelFactory(weatherInteractor: WeatherInteractor): WeatherListViewModelFactory {
        return WeatherListViewModelFactory(weatherInteractor)
    }

    @Singleton
    @Provides
    fun provideMainScreenViewModelFactory(cityRepository: ICityRepository,
                                          mainScreenConverter: MainScreenConverter,
                                          weatherInteractor: WeatherInteractor): MainScreenViewModelFactory {

        return MainScreenViewModelFactory(cityRepository, mainScreenConverter, weatherInteractor)
    }

    @Singleton
    @Provides
    fun provideForecastsRepository(forecastService: ForecastService, context: Context): ForecastsRepository {
        return ForecastsRepositoryImpl(ForecastsDAO.getInstance(context), forecastService)
    }

    @Singleton
    @Provides
    fun provideForecastInteractor(forecastsRepository: ForecastsRepository) = ForecastInteractor(forecastsRepository)

    @Singleton
    @Provides
    fun provideForecastViewModel(forecastInteractor: ForecastInteractor, mainScreenConverter: MainScreenConverter): ForecastViewModelFactory {
        return ForecastViewModelFactory(forecastInteractor, mainScreenConverter)
    }
}