package ru.voronezhtsev.weatherapp.domain;

import io.reactivex.Single;
import ru.voronezhtsev.weatherapp.models.domain.LocationInfo;

public interface ILocationRepository {
    Single<LocationInfo> getLocation();
}
