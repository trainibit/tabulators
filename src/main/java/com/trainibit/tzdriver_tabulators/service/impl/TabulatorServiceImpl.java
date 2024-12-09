package com.trainibit.tzdriver_tabulators.service.impl;

import com.trainibit.tzdriver_tabulators.entity.GeoPolygon;
import com.trainibit.tzdriver_tabulators.entity.Tabulator;
import com.trainibit.tzdriver_tabulators.mapper.GeoPolygonMapper;
import com.trainibit.tzdriver_tabulators.mapper.TabulatorMapper;
import com.trainibit.tzdriver_tabulators.repository.GeoPolygonRepository;
import com.trainibit.tzdriver_tabulators.repository.TabulatorRepository;
import com.trainibit.tzdriver_tabulators.request.TabulatorRequest;
import com.trainibit.tzdriver_tabulators.response.GeoPolygonResponse;
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
                .orElseThrow(() -> new IllegalArgumentException("Tabulator not found with UUID: " + uuidTab));

        return tabulatorMapper.mapEntityToDto(tabulator);
    }

    /*------------ Save Tabulator Method --------------*/

    @Override
    public TabulatorResponse saveTabulator(TabulatorRequest request) {


        GeoPolygon originPolygon = geoPolygonRepository.findByUuidGpAndActiveTrue(request.getOriginpolygonUuidGp())
                .orElseThrow(() -> new IllegalArgumentException("Origin polygon not found"));

        GeoPolygon destinationPolygon = geoPolygonRepository.findByUuidGpAndActiveTrue(request.getDestinationpolygonUuidGp())
                .orElseThrow(() -> new IllegalArgumentException("Destination polygon not found"));

        Tabulator tabulator = tabulatorMapper.mapDtoToEntity(request);

        tabulator.setUuidTab(UUID.randomUUID());
        tabulator.setOriginpolygon(originPolygon);
        tabulator.setDestinationpolygon(destinationPolygon);
        tabulator.setCostTab(request.getCostTab());

        Tabulator savedTabulator = tabulatorRepository.save(tabulator);

        return tabulatorMapper.mapEntityToDto(savedTabulator);
    }



}
