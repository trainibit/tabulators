package com.trainibit.tzdriver_tabulators.response;

import lombok.Data;

import java.util.UUID;

@Data
public class TabulatorResponse {
    private UUID uuidTab;
    private UUID originpolygonUuidGp;
    private UUID destinationpolygonUuidGp;
    private Double costTab;
}
