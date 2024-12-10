package com.trainibit.tzdriver_tabulators.utils;

import com.trainibit.tzdriver_tabulators.entity.GeoPolygonVertex;

import java.util.List;

public class PointInPolygonHelper {

    public static boolean isPointInsidePolygon(double pointLat, double pointLon, List<GeoPolygonVertex> vertices) {
        int crossCount = 0;
        int n = vertices.size();

        for (int i = 0; i < n; i++) {
            GeoPolygonVertex current = vertices.get(i);
            GeoPolygonVertex next = vertices.get((i + 1) % n);

            if (isPointOnEdge(pointLat, pointLon, current, next)) {
                return true; // Si está en el borde, está dentro
            }

            double currentLat = current.getLatitudePv();
            double currentLon = current.getLengthPv();
            double nextLat = next.getLatitudePv();
            double nextLon = next.getLengthPv();

            if (((currentLat > pointLat) != (nextLat > pointLat)) &&
                    (pointLon < (nextLon - currentLon) * (pointLat - currentLat) / (nextLat - currentLat) + currentLon)) {
                crossCount++;
            }
        }

        return crossCount % 2 == 1;
    }

    private static boolean isPointOnEdge(double pointLat, double pointLon, GeoPolygonVertex start, GeoPolygonVertex end) {
        double startLat = start.getLatitudePv();
        double startLon = start.getLengthPv();
        double endLat = end.getLatitudePv();
        double endLon = end.getLengthPv();

        double crossProduct = (pointLat - startLat) * (endLon - startLon) - (pointLon - startLon) * (endLat - startLat);
        if (Math.abs(crossProduct) > 1e-6) {
            return false;
        }

        return pointLat >= Math.min(startLat, endLat) && pointLat <= Math.max(startLat, endLat) &&
                pointLon >= Math.min(startLon, endLon) && pointLon <= Math.max(startLon, endLon);
    }
}
