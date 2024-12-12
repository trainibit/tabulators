package com.trainibit.tzdriver_tabulators.service;

import com.trainibit.tzdriver_tabulators.response.DeterminatePolygonAndTabulatorResponse;
import com.trainibit.tzdriver_tabulators.response.PointInPolygonResponse;

public interface PointInPolygonService {
    PointInPolygonResponse isPointInsidePolygon(double pointLat, double pointLon);
    DeterminatePolygonAndTabulatorResponse isOriginAndDestinationInsidePolygon(double latitudeOrigin, double longitudeOrigin, double latitudeDestination, double longitudeDestination);

}
