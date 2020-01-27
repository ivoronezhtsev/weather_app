package ru.voronezhtsev.weatherapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.voronezhtsev.weatherapp.data.db.ForecastsDAO
import ru.voronezhtsev.weatherapp.data.db.ResponseConverter
import ru.voronezhtsev.weatherapp.data.remote.ForecastsService
import ru.voronezhtsev.weatherapp.data.remote.WeatherService
import ru.voronezhtsev.weatherapp.data.repositories.*
import ru.voronezhtsev.weatherapp.domain.*
import ru.voronezhtsev.weatherapp.presentation.MainScreenConverter
import javax.inject.Singleton

@Module
class WeatherModule {
    @Provides
    @Singleton
    fun provodeForecastsRepository(forecastsService: ForecastsService?, context: Context?): ForecastsRepository {
        return ForecastsRepository(ForecastsDAO.getInstance(context), ResponseConverter(),
                forecastsService)
    }

    @Singleton
    @Provides
    fun provideWeatherService(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }

    @Provides
    @Singleton
    fun provideForecastsService(retrofit: Retrofit): ForecastsService {
        return retrofit.create(ForecastsService::class.java)
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
    fun provideWeatherRepository(weatherService: WeatherService?): IWeatherRepository {
        return DefaultWeatherRepository(weatherService!!)
    }

    @Provides
    @Singleton
    fun provideLocationRepository(context: Context?): ILocationRepository {
        return LocationRepository(context!!)
    }

    @Singleton
    @Provides
    fun provideWeatherLocalRepository(context: Context?): IWeatherLocalRepository {
        return WeatherLocalRepository(context)
    }

    @Provides
    @Singleton
    fun provideWeatherInteractor(locationRepository: ILocationRepository?,
                                 weatherRepository: IWeatherRepository?,
                                 weatherLocalRepository: IWeatherLocalRepository?): WeatherInteractor {
        return WeatherInteractor(weatherRepository!!, locationRepository!!, weatherLocalRepository!!)
    }

    @Singleton
    @Provides
    fun provideCityRepository(context: Context?): ICityRepository {
        return CityRepository(context!!)
    }

    @Singleton
    @Provides
    fun provideMainPresenterFactory(weatherInteractor: WeatherInteractor?, forecastsRepository: ForecastsRepository?,
                                    cityRepository: ICityRepository?, weatherRepository: IWeatherRepository?,
                                    mainScreenConverter: MainScreenConverter): MainPresenterFactory {
        return MainPresenterFactory(weatherInteractor!!, forecastsRepository!!, cityRepository!!, weatherRepository!!, mainScreenConverter)
    }

    @Singleton
    @Provides
    fun privideMainScreenConverter(): MainScreenConverter {
        return MainScreenConverter()
    }
}