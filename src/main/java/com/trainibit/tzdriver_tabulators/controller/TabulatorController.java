package com.trainibit.tzdriver_tabulators.controller;

import com.trainibit.tzdriver_tabulators.request.TabulatorRequest;
import com.trainibit.tzdriver_tabulators.response.GeoPolygonResponse;
import com.trainibit.tzdriver_tabulators.response.TabulatorResponse;
import com.trainibit.tzdriver_tabulators.service.TabulatorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tabulators")
@Validated
public class TabulatorController {

    private final TabulatorService tabulatorService;

    public TabulatorController(TabulatorService tabulatorService) {
        this.tabulatorService = tabulatorService;
    }

    // --------- FindAllActive Method --------

    @GetMapping
    public ResponseEntity<List<TabulatorResponse>> getAllActiveTabulators() {
        List<TabulatorResponse> activeTabulators = tabulatorService.getAllActiveTabulators();
        return ResponseEntity.ok(activeTabulators);
    }

    /*----------- FindByUuid Method ------------*/

    @GetMapping("/{uuidTab}")
    public ResponseEntity<TabulatorResponse> getTabulatorByUuid(@PathVariable UUID uuidTab) {
        TabulatorResponse tabulatorResponse = tabulatorService.getTabulatorByUuid(uuidTab);
        return ResponseEntity.ok(tabulatorResponse);
    }

    /*------------ Save Method -----------------*/

    @PostMapping
    public ResponseEntity<TabulatorResponse> saveTabulator(@Valid @RequestBody TabulatorRequest request) {

        TabulatorResponse response = tabulatorService.saveTabulator(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }


}
