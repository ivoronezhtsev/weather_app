package ru.voronezhtsev.weatherapp.data.repositories

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import io.reactivex.Completable
import io.reactivex.Maybe
import ru.voronezhtsev.weatherapp.domain.ICityChoiceRepository
import java.lang.RuntimeException

class CityChoicePreferencesRepository(private val context: Context) : ICityChoiceRepository {
    companion object {
        private const val PREFS_FILE_NAME = "city_choice";
        private const val KEY_CITY = "cityId";
        private const val DEFAULT_VALUE = Long.MIN_VALUE;
    }

    private var preferences: SharedPreferences = context.getSharedPreferences(PREFS_FILE_NAME, MODE_PRIVATE)

    override val city: Maybe<Long>
        get() {
            return Maybe.fromCallable {
                preferences.getLong(KEY_CITY, DEFAULT_VALUE)
            }.flatMap { cityId ->
                return@flatMap if (cityId == DEFAULT_VALUE) Maybe.empty<Long>() else Maybe.just(cityId)
            }
        }

    override fun save(cityId: Long): Completable {
        return Completable.fromRunnable {
            if (!preferences.edit().putLong(KEY_CITY, cityId).commit()) {
                throw RuntimeException("Save new value of CityId failed")
            }
        }
    }
}