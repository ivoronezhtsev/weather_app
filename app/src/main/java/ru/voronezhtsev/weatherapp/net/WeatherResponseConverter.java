package ru.voronezhtsev.weatherapp.net;

import java.util.ArrayList;
import java.util.List;

import ru.voronezhtsev.weatherapp.db.entities.Forecast;
import ru.voronezhtsev.weatherapp.net.models.WeatherResponse;

/**
 * Конвертер ответа с прогнозом погоды от сервера, в в формат для записи в БД
 *
 * @author Воронежцев Игорь
 */
public class WeatherResponseConverter {

    /**
     * @param response Ответ от сервера с прогнозом погоды
     * @return Прогноз погоды для сохранения в БД
     */
    public static List<Forecast> convert(WeatherResponse response) {
        List<Forecast> forecasts = new ArrayList<>();
        for(ru.voronezhtsev.weatherapp.net.models.Forecast f : response.getForecast()) {
            Forecast forecast = new Forecast(f.getDt(),
                    f.getMain().getTemp(),
                    f.getMain().getTempMin(),
                    f.getMain().getTempMax());
            forecasts.add(forecast);
        }
        return forecasts;
    }
}
