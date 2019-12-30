package ru.voronezhtsev.weatherapp.data.repositories

import android.content.Context
import com.google.gson.Gson
import io.reactivex.Single
import ru.voronezhtsev.weatherapp.domain.ICityRepository
import ru.voronezhtsev.weatherapp.models.domain.City

class CityRepository(private val context: Context) : ICityRepository {

    override val list: Single<Array<City>>
        get() {
            return Single.fromCallable {
                val json_string = context.assets.open("city.json").bufferedReader().use {
                    it.readText()
                }
                val gson = Gson()
                return@fromCallable gson.fromJson(json_string, Array<City>::class.java)
            }
        }
}