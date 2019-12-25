package ru.voronezhtsev.weatherapp.data.repositories

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import io.reactivex.Single
import io.reactivex.SingleEmitter
import ru.voronezhtsev.weatherapp.domain.ILocationRepository
import ru.voronezhtsev.weatherapp.models.domain.Location

class LocationRepository(private val mContext: Context) : ILocationRepository {
    override val location: Single<Location?>
        get() = if (ActivityCompat.checkSelfPermission(mContext, ACCESS_COARSE_LOCATION) != PERMISSION_GRANTED) {
            Single.create { emitter: SingleEmitter<Location?> -> emitter.onError(SecurityException("Need location permissions")) }
        } else Single.create { emitter: SingleEmitter<Location?> ->
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext)
            fusedLocationClient.lastLocation.addOnSuccessListener { location: android.location.Location ->
                val locationInfo = Location(location.latitude.toString(), location.longitude.toString())
                emitter.onSuccess(locationInfo)
            }
        }
}