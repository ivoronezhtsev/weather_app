package ru.voronezhtsev.weatherapp.view;

import ru.voronezhtsev.weatherapp.net.api.WeatherService;

public class MainPresenter {

    private MainView mMainView;

    private WeatherService mWeatherService;

    public MainPresenter() {

    }

    //todo Почитать когда у мокси вызывается этот метод
    public void onAttachView(MainView mainView) {
        mMainView = mainView;

    }

    public void onDetachView() {

    }
}
