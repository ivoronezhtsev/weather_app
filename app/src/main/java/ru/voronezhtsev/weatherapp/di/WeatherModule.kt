package ru.voronezhtsev.weatherapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.voronezhtsev.weatherapp.data.db.ForecastsDAO
import ru.voronezhtsev.weatherapp.data.db.ResponseConverter
import ru.voronezhtsev.weatherapp.data.db.WeatherDao
import ru.voronezhtsev.weatherapp.data.remote.ForecastsService
import ru.voronezhtsev.weatherapp.data.remote.WeatherService
import ru.voronezhtsev.weatherapp.data.repositories.CityRepository
import ru.voronezhtsev.weatherapp.data.repositories.DefaultWeatherRepository
import ru.voronezhtsev.weatherapp.data.repositories.ForecastsRepository
import ru.voronezhtsev.weatherapp.domain.ICityRepository
import ru.voronezhtsev.weatherapp.domain.IWeatherRepository
import ru.voronezhtsev.weatherapp.domain.WeatherInteractor
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
    fun provideWeatherRepository(weatherService: WeatherService?, context: Context): IWeatherRepository {
        return DefaultWeatherRepository(weatherService!!, WeatherDao.getInstance(context))
    }

    @Provides
    @Singleton
    fun provideWeatherInteractor(weatherRepository: IWeatherRepository): WeatherInteractor {
        return WeatherInteractor(weatherRepository)
    }

    @Singleton
    @Provides
    fun provideCityRepository(context: Context): ICityRepository {
        return CityRepository(context)
    }

    @Singleton
    @Provides
    fun provideMainPresenterFactory(cityRepository: ICityRepository,
                                    weatherRepository: IWeatherRepository,
                                    mainScreenConverter: MainScreenConverter,
                                    weatherInteractor: WeatherInteractor): MainPresenterFactory {
        return MainPresenterFactory(cityRepository, weatherRepository, mainScreenConverter, weatherInteractor)
    }

    @Singleton
    @Provides
    fun privideMainScreenConverter(): MainScreenConverter {
        return MainScreenConverter()
    }
}