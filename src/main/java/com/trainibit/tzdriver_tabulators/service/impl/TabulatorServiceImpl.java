package com.trainibit.tzdriver_tabulators.service.impl;

import com.trainibit.tzdriver_tabulators.entity.GeoPolygon;
import com.trainibit.tzdriver_tabulators.entity.Tabulator;
import com.trainibit.tzdriver_tabulators.mapper.TabulatorMapper;
import com.trainibit.tzdriver_tabulators.repository.GeoPolygonRepository;
import com.trainibit.tzdriver_tabulators.repository.TabulatorRepository;
import com.trainibit.tzdriver_tabulators.request.TabulatorRequest;
import com.trainibit.tzdriver_tabulators.response.TabulatorResponse;
import com.trainibit.tzdriver_tabulators.service.TabulatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TabulatorServiceImpl implements TabulatorService {

    @Autowired
    private TabulatorRepository tabulatorRepository;

    @Autowired
    private GeoPolygonRepository geoPolygonRepository;

    @Autowired
    private TabulatorMapper tabulatorMapper;

    /*----------- Geo Polyogns List --------------*/

    @Override
    public List<TabulatorResponse> getAllActiveTabulators() {
        List<Tabulator> activeTabulators = tabulatorRepository.findAllByActiveTrue();

        return activeTabulators.stream()
                .map(tabulatorMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }


    /*------------- Get Tabulator By UUID --------------*/

    @Override
    public TabulatorResponse getTabulatorByUuid(UUID uuidTab) {
        Tabulator tabulator = tabulatorRepository.findByUuidTabAndActiveTrue(uuidTab)
                .orElseThrow(() -> new IllegalArgumentException("Tabulador con UUID: " + uuidTab + " no encontrado."));

        return tabulatorMapper.mapEntityToDto(tabulator);
    }

    /*------------ Search Tabulator For Origin & Destinetion --------------*/

    @Override
    public TabulatorResponse getTabulatorByPolygons(UUID originUuid, UUID destinationUuid) {
        Tabulator tabulator = tabulatorRepository.findByOriginpolygon_UuidGpAndDestinationpolygon_UuidGpAndActiveTrue(originUuid, destinationUuid)
                .orElseThrow(() -> new IllegalArgumentException("Tabulador no disponible para el origen y destino especificados."));

        return tabulatorMapper.mapEntityToDto(tabulator);
    }

    /*------------ Save Tabulator Method --------------*/

    @Override
    public TabulatorResponse saveTabulator(TabulatorRequest request) {

        GeoPolygon originPolygon = geoPolygonRepository.findByUuidGpAndActiveTrue(request.getOriginpolygonUuidGp())
                .orElseThrow(() -> new IllegalArgumentException("Poligono de origen no encontrado."));

        GeoPolygon destinationPolygon = geoPolygonRepository.findByUuidGpAndActiveTrue(request.getDestinationpolygonUuidGp())
                .orElseThrow(() -> new IllegalArgumentException("Poligono de destino no encontrado."));

        Tabulator tabulator = tabulatorMapper.mapDtoToEntity(request);

        tabulator.setUuidTab(UUID.randomUUID());
        tabulator.setOriginpolygon(originPolygon);
        tabulator.setDestinationpolygon(destinationPolygon);
        tabulator.setCostTab(request.getCostTab());

        Tabulator savedTabulator = tabulatorRepository.save(tabulator);

        return tabulatorMapper.mapEntityToDto(savedTabulator);
    }


    /*------------ Update Tabulator Method --------------*/

    @Override
    public TabulatorResponse updateTabulator(UUID uuid, TabulatorRequest requestTabulator) {
        Tabulator existingTabulator = tabulatorRepository.findByUuidTabAndActiveTrue(uuid)
                .orElseThrow(() -> new NoSuchElementException("Tabulador no encontrado con UUID: " + uuid));

        GeoPolygon originPolygon = geoPolygonRepository.findByUuidGpAndActiveTrue(requestTabulator.getOriginpolygonUuidGp())
                .orElseThrow(() -> new IllegalArgumentException("Polígono de origen no encontrado o inactivo."));

        GeoPolygon destinationPolygon = geoPolygonRepository.findByUuidGpAndActiveTrue(requestTabulator.getDestinationpolygonUuidGp())
                .orElseThrow(() -> new IllegalArgumentException("Polígono de destino no encontrado o inactivo."));

        boolean duplicateExists = tabulatorRepository.existsByOriginpolygonAndDestinationpolygonAndActiveTrueAndUuidTabNot(originPolygon, destinationPolygon, uuid);
        if (duplicateExists) {
            throw new IllegalArgumentException("Ya existe un tabulador activo con el mismo origen y destino.");
        }


        existingTabulator.setOriginpolygon(originPolygon);
        existingTabulator.setDestinationpolygon(destinationPolygon);
        existingTabulator.setCostTab(requestTabulator.getCostTab());
        existingTabulator.setUpdatedTab(Timestamp.from(Instant.now()));

        Tabulator updatedTabulator = tabulatorRepository.save(existingTabulator);

        return tabulatorMapper.mapEntityToDto(updatedTabulator);
    }

    /*------------ Delete Tabulator Method --------------*/

    @Override

    public TabulatorResponse deleteTabulator(UUID uuidTab) {
        Tabulator existingTabulator = tabulatorRepository.findByUuidTabAndActiveTrue(uuidTab)
                .orElseThrow(() -> new NoSuchElementException("Tabulador no encontrado con UUID: " + uuidTab));

        existingTabulator.setActive(false);
        existingTabulator.setUpdatedTab(Timestamp.from(Instant.now()));
        tabulatorRepository.save(existingTabulator);
        return tabulatorMapper.mapEntityToDto(existingTabulator);
    }
}
