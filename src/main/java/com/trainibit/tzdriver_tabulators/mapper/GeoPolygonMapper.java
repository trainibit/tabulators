package com.trainibit.tzdriver_tabulators.mapper;

import com.trainibit.tzdriver_tabulators.entity.GeoPolygon;
import com.trainibit.tzdriver_tabulators.entity.GeoPolygonVertex;
import com.trainibit.tzdriver_tabulators.request.GeoPolygonRequest;
import com.trainibit.tzdriver_tabulators.request.PolygonVertexRequest;
import com.trainibit.tzdriver_tabulators.response.GeoPolygonResponse;
import com.trainibit.tzdriver_tabulators.response.PolygonVertexResponse;

import java.util.*;

public class GeoPolygonMapper {

    /*public static GeoPolygon mapDtoToEntity(GeoPolygonRequest request) {
        GeoPolygon geoPolygon = new GeoPolygon();
        geoPolygon.setUuidGp(UUID.randomUUID());
        geoPolygon.setZoneGp(request.getZoneGp());
        geoPolygon.setPolygonVertex(new ArrayList<>());

        for (PolygonVertexRequest vertexRequest : request.getPolygonVertex()) {
            GeoPolygonVertex vertex = new GeoPolygonVertex();
            vertex.setUuidPv(UUID.randomUUID());
            vertex.setOrdenPv(vertexRequest.getOrdenPv());
            vertex.setLatitudePv(vertexRequest.getLatitudePv());
            vertex.setLengthPv(vertexRequest.getLengthPv());
            vertex.setGeoPolygon(geoPolygon);
            geoPolygon.getPolygonVertex().add(vertex);
        }
        return geoPolygon;
    }*/

    public static GeoPolygon mapDtoToEntity(GeoPolygonRequest request) {
        GeoPolygon geoPolygon = new GeoPolygon();
        geoPolygon.setUuidGp(UUID.randomUUID());
        geoPolygon.setZoneGp(request.getZoneGp());
        geoPolygon.setPolygonVertex(
                request.getPolygonVertex().stream()
                        .map(vertexRequest -> {
                            GeoPolygonVertex vertex = new GeoPolygonVertex();
                            vertex.setUuidPv(UUID.randomUUID());
                            vertex.setLatitudePv(vertexRequest.getLatitudePv());
                            vertex.setLengthPv(vertexRequest.getLengthPv());
                            vertex.setOrdenPv(vertexRequest.getOrdenPv());
                            vertex.setGeoPolygon(geoPolygon); // Relación inversa
                            return vertex;
                        })
                        .toList()
        );
        return geoPolygon;
    }


    /*public static GeoPolygonResponse mapEntityToDto(GeoPolygon geoPolygon) {
        GeoPolygonResponse response = new GeoPolygonResponse();
        response.setUuidGp(geoPolygon.getUuidGp());
        response.setZoneGp(geoPolygon.getZoneGp());

        List<PolygonVertexResponse> vertexResponses = geoPolygon.getPolygonVertex().stream()
            .filter(GeoPolygonVertex::getIsActive)
            .map(vertex -> {
                PolygonVertexResponse vertexResponse = new PolygonVertexResponse();
                vertexResponse.setUuidPv(vertex.getUuidPv());
                vertexResponse.setOrdenPv(vertex.getOrdenPv());
                vertexResponse.setLatitudePv(vertex.getLatitudePv());
                vertexResponse.setLengthPv(vertex.getLengthPv());
                return vertexResponse;
            })
            .toList();
        response.setPolygonVertex(vertexResponses);

        return response;
    }*/

    public static GeoPolygonResponse mapEntityToDto(GeoPolygon geoPolygon) {
        GeoPolygonResponse response = new GeoPolygonResponse();
        response.setUuidGp(geoPolygon.getUuidGp());
        response.setZoneGp(geoPolygon.getZoneGp());

        List<PolygonVertexResponse> vertexResponses =
                Optional.ofNullable(geoPolygon.getPolygonVertex())
                        .orElse(Collections.emptyList())
                        .stream()
                        .filter(vertex -> Boolean.TRUE.equals(vertex.getIsActive()))
                        .map(vertex -> {
                            PolygonVertexResponse vertexResponse = new PolygonVertexResponse();
                            vertexResponse.setUuidPv(vertex.getUuidPv());
                            vertexResponse.setOrdenPv(vertex.getOrdenPv());
                            vertexResponse.setLatitudePv(vertex.getLatitudePv());
                            vertexResponse.setLengthPv(vertex.getLengthPv());
                            return vertexResponse;
                        })
                        .toList();
        response.setPolygonVertex(vertexResponses);

        return response;
    }


}
