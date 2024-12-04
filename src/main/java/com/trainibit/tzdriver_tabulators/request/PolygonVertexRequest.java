package com.trainibit.tzdriver_tabulators.request;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class PolygonVertexRequest {
    @NotNull
    private Long ordenPv;

    @NotNull
    private Double latitudePv;

    @NotNull
    private Double lengthPv;
}
