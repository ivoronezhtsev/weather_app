package ru.voronezhtsev.weatherapp.data.repositories;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import io.reactivex.Single;
import ru.voronezhtsev.weatherapp.domain.ILocationRepository;
import ru.voronezhtsev.weatherapp.models.domain.Location;

public class LocationRepository implements ILocationRepository {
    private Context mContext;
    public LocationRepository(@NonNull Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public Single<Location> getLocation() {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return Single.create(emitter -> emitter.onError(new SecurityException("Need location permissions")));
        }
        return Single.create(emitter -> {
            FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext);
            fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
                Location locationInfo = new Location(String.valueOf(location.getLatitude()),
                        String.valueOf(location.getLongitude()));
                emitter.onSuccess(locationInfo);
            });
        });
    }
}
