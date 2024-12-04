package com.trainibit.tzdriver_tabulators.service.impl;

import com.trainibit.tzdriver_tabulators.entity.GeoPolygon;
import com.trainibit.tzdriver_tabulators.mapper.GeoPolygonMapper;
import com.trainibit.tzdriver_tabulators.repository.GeoPolygonRepository;
import com.trainibit.tzdriver_tabulators.request.GeoPolygonRequest;
import com.trainibit.tzdriver_tabulators.response.GeoPolygonResponse;
import com.trainibit.tzdriver_tabulators.service.GeoPolygonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeoPolygonServiceImpl implements GeoPolygonService {
    @Autowired
    private GeoPolygonRepository geoPolygonRepository;

    @Override
    public List<GeoPolygonResponse> getAllActivePolygons() {
        // Obtener todos los polígonos activos desde el repositorio
        List<GeoPolygon> activePolygons = geoPolygonRepository.findAllByIsActiveTrue();

        // Mapear los polígonos activos y sus vértices a una lista de GeoPolygonResponse
        return activePolygons.stream()
                .map(GeoPolygonMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public GeoPolygonResponse save(GeoPolygonRequest requestGeoPolygon) {

        if (requestGeoPolygon.getPolygonVertex().size() < 3) {
            throw new IllegalArgumentException("El polígono debe tener al menos 3 vértices.");
        }

        GeoPolygon geoPolygon = GeoPolygonMapper.mapDtoToEntity(requestGeoPolygon);
        GeoPolygon savedGeoPolygon = geoPolygonRepository.save(geoPolygon);
        return GeoPolygonMapper.mapEntityToDto(savedGeoPolygon);
    }
}
