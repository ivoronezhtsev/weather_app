package ru.voronezhtsev.weatherapp.data.repositories;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import io.reactivex.Single;
import io.reactivex.subjects.PublishSubject;
import ru.voronezhtsev.weatherapp.models.domain.LocationEntity;

public class LocationRepository {
    private Context mContext;
    private FusedLocationProviderClient mFusedLocationClient;
    PublishSubject<Location> mLocationSubject = PublishSubject.create();

    public LocationRepository(@NonNull Context context) {
        mContext = context;
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext);
    }

    public Single<LocationEntity> getLocation() {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            return null;
        }

        mFusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
            mLocationSubject.onNext(location);
            mLocationSubject.onComplete();
        });

        return mLocationSubject.take(1).elementAtOrError(0)
                .flatMap(l -> {
                    ru.voronezhtsev.weatherapp.models.domain.LocationEntity locationEntity =
                            new LocationEntity(String.valueOf(l.getLatitude()),
                                    String.valueOf(l.getLongitude()));
                    return Single.just(locationEntity);
                });
    }

}
