package com.trainibit.tzdriver_tabulators.service;

import com.trainibit.tzdriver_tabulators.request.GeoPolygonRequest;
import com.trainibit.tzdriver_tabulators.response.GeoPolygonResponse;

import java.util.List;

public interface GeoPolygonService {
    GeoPolygonResponse save(GeoPolygonRequest requestGeoPolygon);

    List<GeoPolygonResponse> getAllActivePolygons();
}
