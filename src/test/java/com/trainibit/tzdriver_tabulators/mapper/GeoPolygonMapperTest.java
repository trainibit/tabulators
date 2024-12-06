package com.trainibit.tzdriver_tabulators.mapper;

import com.trainibit.tzdriver_tabulators.entity.GeoPolygon;
import com.trainibit.tzdriver_tabulators.entity.GeoPolygonVertex;
import com.trainibit.tzdriver_tabulators.response.GeoPolygonResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

public class GeoPolygonMapperTest {
    @Test
    public void testMapEntityToDto_WithActiveVertices() {
        // Configurar el polígono con vértices activos
        GeoPolygon polygon = new GeoPolygon();
        polygon.setUuidGp(UUID.randomUUID());
        polygon.setZoneGp("Test Zone");

        GeoPolygonVertex vertex1 = new GeoPolygonVertex();
        vertex1.setUuidPv(UUID.randomUUID());
        vertex1.setOrdenPv(1L);
        vertex1.setLatitudePv(10.0);
        vertex1.setLengthPv(20.0);
        vertex1.setIsActive(true); // Activo

        GeoPolygonVertex vertex2 = new GeoPolygonVertex();
        vertex2.setUuidPv(UUID.randomUUID());
        vertex2.setOrdenPv(2L);
        vertex2.setLatitudePv(15.0);
        vertex2.setLengthPv(25.0);
        vertex2.setIsActive(false); // No activo

        polygon.setPolygonVertex(Arrays.asList(vertex1, vertex2));

        // Ejecutar el método
        GeoPolygonResponse response = GeoPolygonMapper.mapEntityToDto(polygon);

        // Validar resultados
        assertNotNull(response);
        assertEquals(1, response.getPolygonVertex().size());
        assertEquals(vertex1.getUuidPv(), response.getPolygonVertex().get(0).getUuidPv());
    }

    @Test
    public void testMapEntityToDto_WithNullVertexList() {
        // Configurar un polígono con lista de vértices nula
        GeoPolygon polygon = new GeoPolygon();
        polygon.setUuidGp(UUID.randomUUID());
        polygon.setZoneGp("Test Zone");
        polygon.setPolygonVertex(null);

        // Ejecutar el método
        GeoPolygonResponse response = GeoPolygonMapper.mapEntityToDto(polygon);

        // Validar resultados
        assertNotNull(response);
        assertNotNull(response.getPolygonVertex());
        assertTrue(response.getPolygonVertex().isEmpty());
    }

    @Test
    public void testMapEntityToDto_WithEmptyVertexList() {
        // Configurar un polígono con lista de vértices vacía
        GeoPolygon polygon = new GeoPolygon();
        polygon.setUuidGp(UUID.randomUUID());
        polygon.setZoneGp("Test Zone");
        polygon.setPolygonVertex(Collections.emptyList());

        // Ejecutar el método
        GeoPolygonResponse response = GeoPolygonMapper.mapEntityToDto(polygon);

        // Validar resultados
        assertNotNull(response);
        assertNotNull(response.getPolygonVertex());
        assertTrue(response.getPolygonVertex().isEmpty());
    }

    @Test
    public void testMapEntityToDto_WithNullIsActive() {
        // Configurar un polígono con vértices y valores nulos en `isActive`
        GeoPolygon polygon = new GeoPolygon();
        polygon.setUuidGp(UUID.randomUUID());
        polygon.setZoneGp("Test Zone");

        GeoPolygonVertex vertex = new GeoPolygonVertex();
        vertex.setUuidPv(UUID.randomUUID());
        vertex.setOrdenPv(1L);
        vertex.setLatitudePv(10.0);
        vertex.setLengthPv(20.0);
        vertex.setIsActive(null); // Valor nulo

        polygon.setPolygonVertex(Collections.singletonList(vertex));

        // Ejecutar el método
        GeoPolygonResponse response = GeoPolygonMapper.mapEntityToDto(polygon);

        // Validar resultados
        assertNotNull(response);
        assertNotNull(response.getPolygonVertex());
        assertTrue(response.getPolygonVertex().isEmpty());
    }
}

