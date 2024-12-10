package com.trainibit.tzdriver_tabulators.service;

import com.trainibit.tzdriver_tabulators.response.PointInPolygonResponse;

import java.util.UUID;

public interface PointInPolygonService {
    PointInPolygonResponse isPointInsidePolygon(double pointLat, double pointLon);
}
