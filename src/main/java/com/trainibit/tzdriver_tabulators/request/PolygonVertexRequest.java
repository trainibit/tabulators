package com.trainibit.tzdriver_tabulators.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PolygonVertexRequest {
    @NotNull
    private Long ordenPv;

    @NotNull
    private Double latitudePv;

    @NotNull
    private Double lengthPv;
}
