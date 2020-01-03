package ru.voronezhtsev.weatherapp.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import ru.voronezhtsev.weatherapp.data.repositories.ForecastsRepository;
import ru.voronezhtsev.weatherapp.domain.ICityRepository;
import ru.voronezhtsev.weatherapp.domain.WeatherInteractor;
import ru.voronezhtsev.weatherapp.presentation.CityChoicePresenter;
import ru.voronezhtsev.weatherapp.presentation.MainPresenter;

@Singleton
@Component(modules = {WeatherModule.class})
public interface WeatherComponent {


    ForecastsRepository getForecastsRepository();

    WeatherInteractor getWeatherInteractor();

    ICityRepository getCityRepository();

    MainPresenterFactory getMainPresenterFactory();

    CityChoicePresenter getCityChoicePresenter();

    @Component.Builder
    interface Builder {
        WeatherComponent build();
        @BindsInstance
        Builder component(Context context);
    }
}
