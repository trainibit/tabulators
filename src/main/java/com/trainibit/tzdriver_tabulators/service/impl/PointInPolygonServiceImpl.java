package com.trainibit.tzdriver_tabulators.service.impl;

import com.trainibit.tzdriver_tabulators.entity.GeoPolygon;
import com.trainibit.tzdriver_tabulators.entity.Tabulator;
import com.trainibit.tzdriver_tabulators.repository.GeoPolygonRepository;
import com.trainibit.tzdriver_tabulators.repository.TabulatorRepository;
import com.trainibit.tzdriver_tabulators.response.DeterminatePolygonAndTabulatorResponse;
import com.trainibit.tzdriver_tabulators.response.PointInPolygonResponse;
import com.trainibit.tzdriver_tabulators.service.PointInPolygonService;
import com.trainibit.tzdriver_tabulators.utils.PointInPolygonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointInPolygonServiceImpl implements PointInPolygonService {

    private final GeoPolygonRepository geoPolygonRepository;
    @Autowired
    public PointInPolygonServiceImpl(GeoPolygonRepository geoPolygonRepository) {
        this.geoPolygonRepository = geoPolygonRepository;
    }

    @Autowired
    private TabulatorRepository tabulatorRepository;

    @Override
    public PointInPolygonResponse isPointInsidePolygon(double pointLat, double pointLon) {
        GeoPolygon polygon = determinePointInsidePolygon(pointLat, pointLon);
        return new PointInPolygonResponse(polygon.getUuidGp(), polygon.getZoneGp());
    }

    @Override
    public DeterminatePolygonAndTabulatorResponse isOriginAndDestinationInsidePolygon(double latOrigin, double longOrigin, double latDestination, double longDestination) {
        GeoPolygon originPolygon = determinePointInsidePolygon(latOrigin, longOrigin);
        GeoPolygon destinationPolygon = determinePointInsidePolygon(latDestination, longDestination);

        Tabulator tabulator = tabulatorRepository.findByOriginpolygon_UuidGpAndDestinationpolygon_UuidGpAndActiveTrue(
                        originPolygon.getUuidGp(), destinationPolygon.getUuidGp())
                .orElseThrow(() -> new IllegalArgumentException("No se encontró un tabulador entre los polígonos especificados."));

        return new DeterminatePolygonAndTabulatorResponse(
                originPolygon.getUuidGp(),
                originPolygon.getZoneGp(),
                destinationPolygon.getUuidGp(),
                destinationPolygon.getZoneGp(),
                tabulator.getCostTab()
        );
    }


    private GeoPolygon determinePointInsidePolygon(double latitude, double longitude) {
        List<GeoPolygon> polygons = geoPolygonRepository.findAllByActiveTrue();

        return polygons.stream()
                .filter(polygon -> PointInPolygonHelper.isPointInsidePolygon(latitude, longitude, polygon.getPolygonVertex()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("El punto especificado no está dentro de un polígono registrado."));
    }

}

