package com.trainibit.tzdriver_tabulators.request;

import com.trainibit.tzdriver_tabulators.entity.GeoPolygon;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Data
public class GeoPolygonRequest {

    @NotNull
    private String zoneGp;

    @NotNull
    private List<PolygonVertexRequest> polygonVertex;

}
