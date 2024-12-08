package com.trainibit.tzdriver_tabulators.response;

import lombok.Data;

import java.util.UUID;

@Data
public class PolygonVertexResponse {

    private UUID uuidPv;
    private Long ordenPv;
    private Double latitudePv;
    private Double lengthPv;
}
