package com.trainibit.tzdriver_tabulators.controller;


import com.trainibit.tzdriver_tabulators.entity.GeoPolygon;
import com.trainibit.tzdriver_tabulators.entity.GeoPolygonVertex;
import com.trainibit.tzdriver_tabulators.mapper.GeoPolygonMapper;
import com.trainibit.tzdriver_tabulators.repository.GeoPolygonRepository;
import com.trainibit.tzdriver_tabulators.request.GeoPolygonRequest;
import com.trainibit.tzdriver_tabulators.response.GeoPolygonResponse;
import com.trainibit.tzdriver_tabulators.service.GeoPolygonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/geoPolygon")
@Validated
public class GeoPolygonController {

    @Autowired
    GeoPolygonService geoPolygonService;

    // --------- FindAllActive Method --------

    @GetMapping
    public ResponseEntity<List<GeoPolygonResponse>> getAllActivePolygons() {
        List<GeoPolygonResponse> activePolygons = geoPolygonService.getAllActivePolygons();
        return ResponseEntity.ok(activePolygons);
    }

    /*----------- FindByUuid Method ------------*/

    @GetMapping("/{uuid}")
    public ResponseEntity<GeoPolygonResponse> findByUuid(@Valid @PathVariable UUID uuid) {
        GeoPolygonResponse response = geoPolygonService.getPolygonByUuid(uuid);
            return ResponseEntity.ok(response);
    }

    //---------- Save Method -------------

    @PostMapping
    public ResponseEntity<GeoPolygonResponse> createGeoPolygon(@Valid @RequestBody GeoPolygonRequest requestGeoPolygon) {
        GeoPolygonResponse responseGeoPolygon = geoPolygonService.save(requestGeoPolygon);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseGeoPolygon);
    }

    /*----------- Update Method ------------*/

    @PutMapping("/{uuid}")
    public ResponseEntity<GeoPolygonResponse> updatePolygon(
            @PathVariable UUID uuid,
            @Valid @RequestBody GeoPolygonRequest requestGeoPolygon) {

        GeoPolygonResponse updatedPolygon = geoPolygonService.updatePolygon(uuid, requestGeoPolygon);
        return ResponseEntity.ok(updatedPolygon);
    }

    /*----------- Delete Method ------------*/

    @DeleteMapping("/{uuid}")
    public ResponseEntity<GeoPolygonResponse> deletePolygon(@PathVariable UUID uuid) {
        GeoPolygonResponse response = geoPolygonService.deletePolygonByUuid(uuid);
        return ResponseEntity.ok(response);
    }

}
