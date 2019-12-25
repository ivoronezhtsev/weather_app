package ru.voronezhtsev.weatherapp.data.db;

import java.util.ArrayList;
import java.util.List;

import ru.voronezhtsev.weatherapp.models.data.db.Forecast;
import ru.voronezhtsev.weatherapp.models.data.network.ForecastsResponse;


/**
 * Конвертер ответа с прогнозом погоды от сервера, в формат для записи в БД
 *
 * @author Воронежцев Игорь
 */
public class ResponseConverter {

    private static final double TEMP_MIN = 0;
    private static final double TEMP_MAX = 0;

    /**
     * @param response Ответ от сервера с прогнозом погоды
     * @return Прогноз погоды для сохранения в БД
     */
    public List<Forecast> convert(ForecastsResponse response) {
        List<Forecast> forecasts = new ArrayList<>();
        for (ru.voronezhtsev.weatherapp.models.data.network.Forecast f : response.getForecast()) {
            Forecast forecast = new Forecast(f.getDt(),
                    f.getMain().getTemp(), TEMP_MIN, TEMP_MAX);
            forecasts.add(forecast);
        }
        return forecasts;
    }
}
