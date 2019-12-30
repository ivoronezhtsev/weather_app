package ru.voronezhtsev.weatherapp.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import ru.voronezhtsev.weatherapp.data.repositories.ForecastsRepository;
import ru.voronezhtsev.weatherapp.domain.WeatherInteractor;

@Singleton
@Component(modules = {WeatherModule.class})
public interface WeatherComponent {


    ForecastsRepository getForecastsRepository();

    WeatherInteractor getWeatherInteractor();

    @Component.Builder
    interface Builder {
        WeatherComponent build();

        @BindsInstance
        Builder context(Context context);
    }
}
