package com.trainibit.tzdriver_tabulators.request;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class PolygonVertexRequest {
    @NotNull
    private Long ordenPv;

    @NotNull
    private Double latitudePv;

    @NotNull
    private Double lengthPv;
}
