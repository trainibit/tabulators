package com.trainibit.tzdriver_tabulators.response;

import lombok.Data;

import java.util.UUID;

@Data
public class PointInPolygonResponse {
    private UUID uuidGp;
    private String zoneGp;

    public PointInPolygonResponse(UUID uuidGp, String zoneGp) {
        this.uuidGp = uuidGp;
        this.zoneGp = zoneGp;
    }
}
