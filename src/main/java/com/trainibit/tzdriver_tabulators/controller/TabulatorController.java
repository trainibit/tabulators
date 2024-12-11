package com.trainibit.tzdriver_tabulators.controller;

import com.trainibit.tzdriver_tabulators.request.TabulatorRequest;
import com.trainibit.tzdriver_tabulators.response.TabulatorResponse;
import com.trainibit.tzdriver_tabulators.service.TabulatorService;
import jakarta.validation.Valid;
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

    /*------------ Search Tabulator By Origin & Destination Method -----------------*/

    @GetMapping("/search")
    public ResponseEntity<TabulatorResponse> getTabulatorByPolygons(
            @RequestParam UUID originUuid,
            @RequestParam UUID destinationUuid) {
        // Llamar al servicio para obtener el Tabulator por los UUIDs de los polígonos
        TabulatorResponse tabulatorResponse = tabulatorService.getTabulatorByPolygons(originUuid, destinationUuid);

        // Retornar la respuesta con el código HTTP 200 OK
        return ResponseEntity.ok(tabulatorResponse);
    }

    /*------------ Save Method -----------------*/

    @PostMapping
    public ResponseEntity<TabulatorResponse> saveTabulator(@Valid @RequestBody TabulatorRequest request) {

        TabulatorResponse response = tabulatorService.saveTabulator(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    /*----------- Update Method ------------*/

    @PutMapping("/{uuid}")
    public ResponseEntity<TabulatorResponse> updateTabulator(@PathVariable UUID uuid, @Valid @RequestBody TabulatorRequest requestTabulator) {
        TabulatorResponse updatedTabulator = tabulatorService.updateTabulator(uuid, requestTabulator);
        return ResponseEntity.ok(updatedTabulator);
    }

    /*----------- Update Method ------------*/
    @DeleteMapping("/{uuid}")
    public ResponseEntity<TabulatorResponse> deleteTabulator(@PathVariable UUID uuid) {
        TabulatorResponse tabulatorResponse = tabulatorService.deleteTabulator(uuid);
        return ResponseEntity.ok(tabulatorResponse);
    }


}
