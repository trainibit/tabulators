package com.trainibit.tzdriver_tabulators.service.impl;

import com.trainibit.tzdriver_tabulators.entity.GeoPolygon;
import com.trainibit.tzdriver_tabulators.entity.GeoPolygonVertex;
import com.trainibit.tzdriver_tabulators.mapper.GeoPolygonMapper;
import com.trainibit.tzdriver_tabulators.repository.GeoPolygonRepository;
import com.trainibit.tzdriver_tabulators.repository.GeoPolygonVertexRepository;
import com.trainibit.tzdriver_tabulators.request.GeoPolygonRequest;
import com.trainibit.tzdriver_tabulators.request.PolygonVertexRequest;
import com.trainibit.tzdriver_tabulators.response.GeoPolygonResponse;
import com.trainibit.tzdriver_tabulators.service.GeoPolygonService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
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
        List<GeoPolygon> activePolygons = geoPolygonRepository.findAllByActiveTrue();

        return activePolygons.stream()
                .map(GeoPolygonMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    /*------------- Geo Polygon By UUID ---------------*/
    @Override
    public GeoPolygonResponse getPolygonByUuid(UUID uuid) {
        return geoPolygonRepository.findByUuidGp(uuid)
                .filter(GeoPolygon::getActive) // Filtrar si el polígono está activo
                .map(GeoPolygonMapper::mapEntityToDto) // Mapear a DTO
                .orElseThrow(() -> new NoSuchElementException("Polígono con UUID " + uuid + " no encontrado."));
    }

    /*------------- Geo Polygon Save ---------------*/
    @Override
    @Transactional
    public GeoPolygonResponse save(GeoPolygonRequest requestGeoPolygon) {

        if (requestGeoPolygon.getPolygonVertex() == null || requestGeoPolygon.getPolygonVertex().size() < 3) {
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
        GeoPolygon existingPolygon = geoPolygonRepository.findByUuidGp(uuidGp)
                .orElseThrow(() -> new NoSuchElementException("Polígono no encontrado."));

        if (requestGeoPolygon.getPolygonVertex() == null || requestGeoPolygon.getPolygonVertex().size() < 3) {
            throw new IllegalArgumentException("El polígono debe tener al menos 3 vértices.");
        }

        for (GeoPolygonVertex vertex : existingPolygon.getPolygonVertex()) {
            vertex.setActive(false);
            vertex.setUpdatedPv(Timestamp.from(Instant.now()));

        }

        List<GeoPolygonVertex> newVertices = new ArrayList<>();
        for (PolygonVertexRequest vertexRequest : requestGeoPolygon.getPolygonVertex()) {
            GeoPolygonVertex newVertex = new GeoPolygonVertex();
            newVertex.setUuidPv(UUID.randomUUID());
            newVertex.setOrdenPv(vertexRequest.getOrdenPv());
            newVertex.setLatitudePv(vertexRequest.getLatitudePv());
            newVertex.setLengthPv(vertexRequest.getLengthPv());
            newVertex.setGeoPolygon(existingPolygon);
            newVertex.setActive(true);
            newVertices.add(newVertex);
        }

        existingPolygon.setZoneGp(requestGeoPolygon.getZoneGp());
        existingPolygon.setUpdatedGp(Timestamp.from(Instant.now()));

        existingPolygon.setPolygonVertex(newVertices);

        GeoPolygon updatedPolygon = geoPolygonRepository.save(existingPolygon);

        return GeoPolygonMapper.mapEntityToDto(updatedPolygon);
    }

    /*------------- Geo Polygon Delete ---------------*/
    @Override
    @Transactional
    public String deletePolygonByUuid(UUID uuid) {
        GeoPolygon existingPolygon = geoPolygonRepository.findByUuidGp(uuid)
                .orElseThrow(() -> new NoSuchElementException("Polígono con UUID "+ uuid +" no encontrado."));

        if (!existingPolygon.getActive()) {
            throw new NoSuchElementException("Polígono con UUID "+ uuid +" no encontrado.");  // Lanza una excepción personalizada si no está activo
        }


        for (GeoPolygonVertex vertex : existingPolygon.getPolygonVertex()) {
            vertex.setUpdatedPv(Timestamp.from(Instant.now()));
            vertex.setActive(false);
        }

        existingPolygon.setActive(false);
        existingPolygon.setUpdatedGp(Timestamp.from(Instant.now()));


        geoPolygonRepository.save(existingPolygon);
        geoPolygonVertexRepository.saveAll(existingPolygon.getPolygonVertex());

        return "El polígono con UUID " + uuid + " ha sido eliminado con éxito.";
    }

}
