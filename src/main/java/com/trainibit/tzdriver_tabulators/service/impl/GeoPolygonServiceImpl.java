package com.trainibit.tzdriver_tabulators.service.impl;

import com.trainibit.tzdriver_tabulators.entity.GeoPolygon;
import com.trainibit.tzdriver_tabulators.entity.GeoPolygonVertex;
import com.trainibit.tzdriver_tabulators.entity.Tabulator;
import com.trainibit.tzdriver_tabulators.mapper.GeoPolygonMapper;
import com.trainibit.tzdriver_tabulators.repository.GeoPolygonRepository;
import com.trainibit.tzdriver_tabulators.repository.GeoPolygonVertexRepository;
import com.trainibit.tzdriver_tabulators.repository.TabulatorRepository;
import com.trainibit.tzdriver_tabulators.request.GeoPolygonRequest;
import com.trainibit.tzdriver_tabulators.request.PolygonVertexRequest;
import com.trainibit.tzdriver_tabulators.response.GeoPolygonResponse;
import com.trainibit.tzdriver_tabulators.service.GeoPolygonService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class GeoPolygonServiceImpl implements GeoPolygonService {

    private final GeoPolygonRepository geoPolygonRepository;

    private final GeoPolygonVertexRepository geoPolygonVertexRepository;

    private final TabulatorRepository tabulatorRepository;

    @Autowired
    public GeoPolygonServiceImpl(GeoPolygonRepository geoPolygonRepository, GeoPolygonVertexRepository geoPolygonVertexRepository, TabulatorRepository tabulatorRepository) {
        this.geoPolygonRepository = geoPolygonRepository;
        this.geoPolygonVertexRepository = geoPolygonVertexRepository;
        this.tabulatorRepository = tabulatorRepository;
    }

    /*----------- Geo Polyogns List --------------*/

    @Override
    public List<GeoPolygonResponse> getAllActivePolygons() {
        List<GeoPolygon> activePolygons = geoPolygonRepository.findAllByActiveTrue();

        return activePolygons.stream()
                .map(GeoPolygonMapper::mapEntityToDto)
                .toList();
    }

    /*------------- Geo Polygon By UUID ---------------*/

    @Override
    public GeoPolygonResponse getPolygonByUuid(UUID uuid) {
        GeoPolygon geoPolygon = geoPolygonRepository.findByUuidGpAndActiveTrue(uuid)
                .orElseThrow(() -> new NoSuchElementException("Polígono con UUID " + uuid + " no encontrado."));
         return GeoPolygonMapper.mapEntityToDto(geoPolygon);
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
        GeoPolygon existingPolygon = geoPolygonRepository.findByUuidGpAndActiveTrue(uuidGp)
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
    public GeoPolygonResponse deletePolygonByUuid(UUID uuid) {
        GeoPolygon existingPolygon = findActivePolygon(uuid);

        deactivateVertices(existingPolygon);

        deactivateRelatedTabulators(existingPolygon);

        deactivatePolygon(existingPolygon);

        return GeoPolygonMapper.mapEntityToDto(existingPolygon);

    }

    private GeoPolygon findActivePolygon(UUID uuid) {
        return geoPolygonRepository.findByUuidGpAndActiveTrue(uuid)
                .orElseThrow(() -> new NoSuchElementException("Polígono con UUID " + uuid + " no encontrado."));
    }

    private void deactivateVertices(GeoPolygon polygon) {
        polygon.getPolygonVertex().forEach(vertex -> {
            vertex.setActive(false);
            vertex.setUpdatedPv(Timestamp.from(Instant.now()));
        });
        geoPolygonVertexRepository.saveAll(polygon.getPolygonVertex());
    }

    private void deactivateRelatedTabulators(GeoPolygon polygon) {
        List<Tabulator> relatedTabulators = tabulatorRepository.findByOriginOrDestination(polygon);
        if (!relatedTabulators.isEmpty()) {
            relatedTabulators.forEach(tabulator -> {
                tabulator.setActive(false);
                tabulator.setUpdatedTab(Timestamp.from(Instant.now()));
            });
            tabulatorRepository.saveAll(relatedTabulators);
        }
    }

    private void deactivatePolygon(GeoPolygon polygon) {
        polygon.setActive(false);
        polygon.setUpdatedGp(Timestamp.from(Instant.now()));
        geoPolygonRepository.save(polygon);
    }

}
