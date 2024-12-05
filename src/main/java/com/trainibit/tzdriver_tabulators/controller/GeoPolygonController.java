package com.trainibit.tzdriver_tabulators.controller;


import com.trainibit.tzdriver_tabulators.request.GeoPolygonRequest;
import com.trainibit.tzdriver_tabulators.response.GeoPolygonResponse;
import com.trainibit.tzdriver_tabulators.service.GeoPolygonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/geoPolygon")
@Validated
public class GeoPolygonController {

    @Autowired
    GeoPolygonService geoPolygonService;

    @GetMapping("/")
    public String home() {
        return "Bienvenido a mi aplicación Spring Boot";
    }

    // --------- FindAllActive Method --------

    @GetMapping
    public ResponseEntity<List<GeoPolygonResponse>> getAllActivePolygons() {

        List<GeoPolygonResponse> activePolygons = geoPolygonService.getAllActivePolygons();
        return ResponseEntity.ok(activePolygons);
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

}
