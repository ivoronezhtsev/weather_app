package ru.voronezhtsev.weatherapp.data.db;

import java.util.ArrayList;
import java.util.List;

import ru.voronezhtsev.weatherapp.models.data.db.Forecast;
import ru.voronezhtsev.weatherapp.models.data.network.forecast.ForecastsResponse;

/**
 * Конвертер ответа с прогнозом погоды от сервера, в формат для записи в БД
 *
 * @author Воронежцев Игорь
 */
public class ResponseConverter {

    /**
     * @param response Ответ от сервера с прогнозом погоды
     * @return Прогноз погоды для сохранения в БД
     */
    public List<Forecast> convert(ForecastsResponse response) {
        List<Forecast> forecasts = new ArrayList<>();
        for (ru.voronezhtsev.weatherapp.models.data.network.forecast.Forecast f : response.getForecast()) {
            Forecast forecast = new Forecast(f.getDt(),
                    f.getMain().getTemp(),
                    f.getMain().getTempMin(),
                    f.getMain().getTempMax());
            forecasts.add(forecast);
        }
        return forecasts;
    }
}
