package com.trainibit.tzdriver_tabulators.service;

import com.trainibit.tzdriver_tabulators.response.PointInPolygonResponse;

public interface PointInPolygonService {
    PointInPolygonResponse isPointInsidePolygon(double pointLat, double pointLon);
}
