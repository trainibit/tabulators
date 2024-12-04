package com.trainibit.tzdriver_tabulators.mapper;

import com.trainibit.tzdriver_tabulators.entity.GeoPolygon;
import com.trainibit.tzdriver_tabulators.entity.PolygonVertex;
import com.trainibit.tzdriver_tabulators.request.GeoPolygonRequest;
import com.trainibit.tzdriver_tabulators.request.PolygonVertexRequest;
import com.trainibit.tzdriver_tabulators.response.GeoPolygonResponse;
import com.trainibit.tzdriver_tabulators.response.PolygonVertexResponse;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GeoPolygonMapper {

    public static GeoPolygon mapDtoToEntity(GeoPolygonRequest request) {
        GeoPolygon geoPolygon = new GeoPolygon();
        geoPolygon.setUuidGp(UUID.randomUUID());
        geoPolygon.setZoneGp(request.getZoneGp());
        geoPolygon.setPolygonVertex(new ArrayList<>());

        for (PolygonVertexRequest vertexRequest : request.getPolygonVertex()) {
            PolygonVertex vertex = new PolygonVertex();
            vertex.setUuidPv(UUID.randomUUID());
            vertex.setOrdenPv(vertexRequest.getOrdenPv());
            vertex.setLatitudePv(vertexRequest.getLatitudePv());
            vertex.setLengthPv(vertexRequest.getLengthPv());
            vertex.setGeoPolygon(geoPolygon);
            geoPolygon.getPolygonVertex().add(vertex);
        }
        return geoPolygon;
    }

    public static GeoPolygonResponse mapEntityToDto(GeoPolygon geoPolygon) {
        GeoPolygonResponse response = new GeoPolygonResponse();
        response.setUuidGp(geoPolygon.getUuidGp());
        response.setZoneGp(geoPolygon.getZoneGp());

        List<PolygonVertexResponse> vertexResponses = new ArrayList<>();
        for (PolygonVertex vertex : geoPolygon.getPolygonVertex()) {
            PolygonVertexResponse vertexResponse = new PolygonVertexResponse();
            vertexResponse.setUuidPv(vertex.getUuidPv());
            vertexResponse.setOrdenPv(vertex.getOrdenPv());
            vertexResponse.setLatitudePv(vertex.getLatitudePv());
            vertexResponse.setLengthPv(vertex.getLengthPv());
            vertexResponses.add(vertexResponse);
        }
        response.setPolygonVertex(vertexResponses);

        return response;
    }
}
