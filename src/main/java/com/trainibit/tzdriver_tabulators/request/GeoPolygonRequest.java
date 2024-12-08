package com.trainibit.tzdriver_tabulators.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class GeoPolygonRequest {

    @NotNull
    private String zoneGp;

    @NotNull
    private List<PolygonVertexRequest> polygonVertex;

}
