package com.trainibit.tzdriver_tabulators.response;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class GeoPolygonResponse {
    private UUID uuidGp;
    private String zoneGp;
    private List<PolygonVertexResponse> polygonVertex;
}
