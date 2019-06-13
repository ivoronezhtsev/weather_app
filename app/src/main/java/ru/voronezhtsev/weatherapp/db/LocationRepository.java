package ru.voronezhtsev.weatherapp.db;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import rx.subjects.PublishSubject;

public class LocationRepository {
    private Context mContext;
    private LocationManager mLocationManager;
    PublishSubject<Location> mLocationSubject = PublishSubject.create();
    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            mLocationSubject.onNext(location);
            mLocationSubject.onCompleted();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d("Listener", provider + " status changed");
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.d("Listener", provider + " enabled");
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.d("Listener", provider + " disabled");
        }
    };

    public LocationRepository(@NonNull Context context) {
        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        mContext = context;
    }

    public PublishSubject<Location> getLocation() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        mLocationManager.requestSingleUpdate(criteria, mLocationListener, null);
        return mLocationSubject;
    }

}
