package com.trainibit.tzdriver_tabulators.controller;

import com.trainibit.tzdriver_tabulators.response.PointInPolygonResponse;
import com.trainibit.tzdriver_tabulators.service.PointInPolygonService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pointInPolygon")
@Validated

public class PointInPolygonController {

    private final PointInPolygonService pointInPolygonService;

    public PointInPolygonController(PointInPolygonService pointInPolygonService) {
        this.pointInPolygonService = pointInPolygonService;
    }

    @GetMapping("/find")
    public ResponseEntity<PointInPolygonResponse> isPointInsidePolygon(
            @RequestParam double latitude,
            @RequestParam double longitude) {

        PointInPolygonResponse response = pointInPolygonService.isPointInsidePolygon(latitude, longitude);
        return ResponseEntity.ok(response);
    }





}
