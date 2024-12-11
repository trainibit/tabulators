package com.trainibit.tzdriver_tabulators.service.impl;

import com.trainibit.tzdriver_tabulators.entity.GeoPolygon;
import com.trainibit.tzdriver_tabulators.entity.GeoPolygonVertex;
import com.trainibit.tzdriver_tabulators.repository.GeoPolygonRepository;
import com.trainibit.tzdriver_tabulators.response.PointInPolygonResponse;
import com.trainibit.tzdriver_tabulators.service.PointInPolygonService;
import com.trainibit.tzdriver_tabulators.utils.PointInPolygonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointInPolygonServiceImpl implements PointInPolygonService {

    @Autowired
    private GeoPolygonRepository geoPolygonRepository;

    @Override
    public PointInPolygonResponse isPointInsidePolygon(double pointLat, double pointLon) {
        List<GeoPolygon> polygons = geoPolygonRepository.findAllByActiveTrue();

        // Iterar sobre todos los polígonos activos
        for (GeoPolygon polygon : polygons) {
            List<GeoPolygonVertex> vertices = polygon.getPolygonVertex();

            // Verificar si el punto está dentro del polígono actual
            boolean isInside = PointInPolygonHelper.isPointInsidePolygon(pointLat, pointLon, vertices);

            if (isInside) {
                // Si el punto está dentro, devolver el UUID y nombre de la zona
                return new PointInPolygonResponse(polygon.getUuidGp(), polygon.getZoneGp());
            }
        }

        // Si no se encuentra ningún polígono, lanzar excepción
        throw new IllegalArgumentException("El punto especificado no está dentro de un polígono registrado.");
    }
}

