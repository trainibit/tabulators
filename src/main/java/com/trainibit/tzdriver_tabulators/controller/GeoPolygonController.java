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
        // Llamar al servicio para obtener los polígonos activos
        List<GeoPolygonResponse> activePolygons = geoPolygonService.getAllActivePolygons();

        // Retornar la respuesta
        return ResponseEntity.ok(activePolygons);
    }

    //---------- Save Method -------------

    @PostMapping
    public ResponseEntity<GeoPolygonResponse> createGeoPolygon(@Valid @RequestBody GeoPolygonRequest requestGeoPolygon) {
        GeoPolygonResponse responseGeoPolygon = geoPolygonService.save(requestGeoPolygon);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseGeoPolygon);
    }

}
