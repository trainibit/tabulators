package com.trainibit.tzdriver_tabulators.response;

import lombok.Data;

import java.util.UUID;
@Data

public class DeterminatePolygonAndTabulatorResponse {
        private UUID uuidOrigin;
        private String zoneOrigin;
        private UUID uuidDestination;
        private String zoneDestination;
        private Double costTab;

        public DeterminatePolygonAndTabulatorResponse(UUID uuidOrigin, String zoneOrigin, UUID uuidDestination, String zoneDestination, Double costTab) {
            this.uuidOrigin = uuidOrigin;
            this.zoneOrigin = zoneOrigin;
            this.uuidDestination = uuidDestination;
            this.zoneDestination = zoneDestination;
            this.costTab = costTab;
        }

}
