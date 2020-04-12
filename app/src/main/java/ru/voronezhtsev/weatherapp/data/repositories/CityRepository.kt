package ru.voronezhtsev.weatherapp.data.repositories

import android.content.Context
import com.google.gson.Gson
import io.reactivex.Single
import ru.voronezhtsev.weatherapp.domain.ICityRepository
import ru.voronezhtsev.weatherapp.models.data.assets.City
import ru.voronezhtsev.weatherapp.models.domain.CityModel

class CityRepository(private val context: Context) : ICityRepository {
    override val list: Single<Array<CityModel>>
        get() = Single.fromCallable {
            val json = context.assets.open(CITY_LIST_FILE).bufferedReader().use { it.readText() }
            val gson = Gson()
            return@fromCallable gson.fromJson(json, Array<City>::class.java)
        }.flatMap {
            return@flatMap Single.just(it.map { CityModel(it.id.toLong(), it.name) }.toTypedArray())
        }

    companion object {
        private const val CITY_LIST_FILE = "city.list.short.json"
    }
}
