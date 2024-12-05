package com.trainibit.tzdriver_tabulators.service.impl;

import com.trainibit.tzdriver_tabulators.entity.GeoPolygon;
import com.trainibit.tzdriver_tabulators.entity.GeoPolygonVertex;
import com.trainibit.tzdriver_tabulators.mapper.GeoPolygonMapper;
import com.trainibit.tzdriver_tabulators.repository.GeoPolygonRepository;
import com.trainibit.tzdriver_tabulators.repository.GeoPolygonVertexRepository;
import com.trainibit.tzdriver_tabulators.request.GeoPolygonRequest;
import com.trainibit.tzdriver_tabulators.response.GeoPolygonResponse;
import com.trainibit.tzdriver_tabulators.service.GeoPolygonService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GeoPolygonServiceImpl implements GeoPolygonService {
    @Autowired
    private GeoPolygonRepository geoPolygonRepository;

    @Autowired
    private GeoPolygonVertexRepository geoPolygonVertexRepository;

    /*----------- Geo Polyogns List --------------*/

    @Override
    public List<GeoPolygonResponse> getAllActivePolygons() {
        // Obtener todos los polígonos activos desde el repositorio
        List<GeoPolygon> activePolygons = geoPolygonRepository.findAllByIsActiveTrue();

        // Mapear los polígonos activos y sus vértices a una lista de GeoPolygonResponse
        return activePolygons.stream()
                .map(GeoPolygonMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    /*------------- Geo Polygon Save ---------------*/

    public GeoPolygonResponse save(GeoPolygonRequest requestGeoPolygon) {

        if (requestGeoPolygon.getPolygonVertex().size() < 3) {
            throw new IllegalArgumentException("El polígono debe tener al menos 3 vértices.");
        }

        GeoPolygon geoPolygon = GeoPolygonMapper.mapDtoToEntity(requestGeoPolygon);
        GeoPolygon savedGeoPolygon = geoPolygonRepository.save(geoPolygon);
        return GeoPolygonMapper.mapEntityToDto(savedGeoPolygon);
    }

    /*------------- Geo Polygon Update ---------------*/
    @Override
    @Transactional
    public GeoPolygonResponse updatePolygon(UUID uuidGp, GeoPolygonRequest requestGeoPolygon) {
        // Buscar el polígono por su UUID
        GeoPolygon existingPolygon = geoPolygonRepository.findByUuidGp(uuidGp)
                .orElseThrow(() -> new NoSuchElementException("Polígono no encontrado con UUID: " + uuidGp));
        // Validación de la cantidad de vértices
        if (requestGeoPolygon.getPolygonVertex().size() < 3) {
            throw new IllegalArgumentException("El polígono debe tener al menos 3 vértices.");
        }
        // Actualizar los vértices actuales a inactivos
        existingPolygon.getPolygonVertex().stream()
                .filter(GeoPolygonVertex::getIsActive)
                .forEach(vertex -> {
                    vertex.setIsActive(false);
                    vertex.setUpdatedPv(Timestamp.from(Instant.now()));
                });
        geoPolygonVertexRepository.saveAll(existingPolygon.getPolygonVertex());

        // Registrar los nuevos vértices
        List<GeoPolygonVertex> newVertices = requestGeoPolygon.getPolygonVertex().stream()
                .map(vertexRequest -> {
                    GeoPolygonVertex newVertex = new GeoPolygonVertex();
                    newVertex.setUuidPv(UUID.randomUUID());
                    newVertex.setGeoPolygon(existingPolygon);
                    newVertex.setLatitudePv(vertexRequest.getLatitudePv());
                    newVertex.setLengthPv(vertexRequest.getLengthPv());
                    newVertex.setOrdenPv(vertexRequest.getOrdenPv());
                    return newVertex;
                })
                .toList();
        geoPolygonVertexRepository.saveAll(newVertices); // Guardar los nuevos vértices

        if (!existingPolygon.getZoneGp().equals(requestGeoPolygon.getZoneGp())) {
            existingPolygon.setZoneGp(requestGeoPolygon.getZoneGp());
        }
        existingPolygon.setUpdatedGp(Timestamp.from(Instant.now()));
        GeoPolygon updatedPolygon = geoPolygonRepository.save(existingPolygon);
        return GeoPolygonMapper.mapEntityToDto(updatedPolygon);
    }
}
