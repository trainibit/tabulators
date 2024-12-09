package com.trainibit.tzdriver_tabulators.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class TabulatorRequest {
    @NotNull
    private UUID originpolygonUuidGp;
    @NotNull
    private UUID destinationpolygonUuidGp;
    @NotNull
    private Double costTab;
}
