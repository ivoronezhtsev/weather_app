package ru.voronezhtsev.weatherapp.data.repositories

import io.reactivex.Single
import ru.voronezhtsev.weatherapp.data.APP_ID
import ru.voronezhtsev.weatherapp.data.db.ForecastsDAO
import ru.voronezhtsev.weatherapp.data.remote.ForecastService
import ru.voronezhtsev.weatherapp.domain.ForecastsRepository
import ru.voronezhtsev.weatherapp.models.data.network.ForecastResponse
import ru.voronezhtsev.weatherapp.models.domain.Forecast

class ForecastsRepositoryImpl(private val forecastsDAO: ForecastsDAO,
                              private val forecastService: ForecastService) : ForecastsRepository {

    override fun loadFromNetwork(cityId: Long): Single<List<Forecast>> {
        return forecastService.getForecast(cityId.toString(), APP_ID)
                .flatMap {
                    forecastsDAO.save(it)
                    return@flatMap Single.just(convert(it))
                }
    }

    private fun convert(response: ForecastResponse): List<Forecast> {
        val result = mutableListOf<Forecast>()
        for (forecast in response.forecast) {
            result.add(Forecast(response.city.name, forecast.dateTime, forecast.main.temp - 273.15,
                    forecast.weather[0].icon, forecast.weather[0].description))
        }
        return result
    }

    private fun convertToDb(forecastResponse: ForecastResponse) {
        val forecast = mutableListOf<ru.voronezhtsev.weatherapp.models.data.network.Forecast>()

    }

    override fun loadFromDb(cityName: String) = Single.fromCallable { forecastsDAO.load(cityName) }

}
