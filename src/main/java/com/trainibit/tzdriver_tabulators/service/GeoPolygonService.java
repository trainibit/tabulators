package com.trainibit.tzdriver_tabulators.service;

import com.trainibit.tzdriver_tabulators.request.GeoPolygonRequest;
import com.trainibit.tzdriver_tabulators.response.GeoPolygonResponse;

import java.util.List;
import java.util.UUID;

public interface GeoPolygonService {
    GeoPolygonResponse save(GeoPolygonRequest requestGeoPolygon);

    List<GeoPolygonResponse> getAllActivePolygons();

    GeoPolygonResponse getPolygonByUuid(UUID id);

    GeoPolygonResponse updatePolygon(UUID uuidGp, GeoPolygonRequest requestGeoPolygon);

    GeoPolygonResponse deletePolygonByUuid(UUID uuid);
}
