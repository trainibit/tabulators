package com.trainibit.tzdriver_tabulators.service.impl;

import com.trainibit.tzdriver_tabulators.entity.GeoPolygon;
import com.trainibit.tzdriver_tabulators.repository.GeoPolygonRepository;
import com.trainibit.tzdriver_tabulators.response.GeoPolygonResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class GeoPolygonServiceImplTest {
    @Mock
    private GeoPolygonRepository geoPolygonRepository;

    @InjectMocks
    private GeoPolygonServiceImpl geoPolygonService;

    @Test
    void getAllActivePolygons_ShouldReturnListOfPolygons_WhenActivePolygonsExist() {

        // Arrange
        GeoPolygon polygon1 = new GeoPolygon();
        polygon1.setUuidGp(UUID.randomUUID());
        polygon1.setZoneGp("Zone 1");
        polygon1.setPolygonVertex(new ArrayList<>());

        GeoPolygon polygon2 = new GeoPolygon();
        polygon2.setUuidGp(UUID.randomUUID());
        polygon2.setZoneGp("Zone 2");
        polygon2.setPolygonVertex(new ArrayList<>());

        List<GeoPolygon> activePolygons = List.of(polygon1, polygon2);

        Mockito.when(geoPolygonRepository.findAllByActiveTrue())
                .thenReturn(activePolygons);

        // Act
        List<GeoPolygonResponse> result = geoPolygonService.getAllActivePolygons();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        GeoPolygonResponse response1 = result.get(0);
        GeoPolygonResponse response2 = result.get(1);

        assertEquals(polygon1.getUuidGp(), response1.getUuidGp());
        assertEquals(polygon1.getZoneGp(), response1.getZoneGp());

        assertEquals(polygon2.getUuidGp(), response2.getUuidGp());
        assertEquals(polygon2.getZoneGp(), response2.getZoneGp());

        Mockito.verify(geoPolygonRepository, Mockito.times(1)).findAllByActiveTrue();
    }

    @Test
    void getAllActivePolygons_ShouldReturnEmptyList_WhenNoActivePolygonsExist() {
        // Arrange
        Mockito.when(geoPolygonRepository.findAllByActiveTrue())
                .thenReturn(new ArrayList<>());

        // Act
        List<GeoPolygonResponse> result = geoPolygonService.getAllActivePolygons();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        Mockito.verify(geoPolygonRepository, Mockito.times(1)).findAllByActiveTrue();
    }

    @Test
    void getPolygonByUuid_ShouldReturnPolygon_WhenUuidExists() {
        // Arrange
        UUID testUuid = UUID.randomUUID();
        GeoPolygon mockPolygon = new GeoPolygon();
        mockPolygon.setUuidGp(testUuid);
        mockPolygon.setZoneGp("Test Zone");
        mockPolygon.setPolygonVertex(Arrays.asList());

        Mockito.when(geoPolygonRepository.findByUuidGpAndActiveTrue(testUuid))
                .thenReturn(Optional.of(mockPolygon));

        // Act
        GeoPolygonResponse result = geoPolygonService.getPolygonByUuid(testUuid);

        // Assert
        assertNotNull(result);
        assertEquals(testUuid, result.getUuidGp());
        assertEquals("Test Zone", result.getZoneGp());
        Mockito.verify(geoPolygonRepository, Mockito.times(1)).findByUuidGpAndActiveTrue(testUuid);
    }

    @Test
    void getPolygonByUuid_ShouldThrowException_WhenUuidDoesNotExist() {
        // Arrange
        UUID testUuid = UUID.randomUUID();

        Mockito.when(geoPolygonRepository.findByUuidGpAndActiveTrue(testUuid))
                .thenReturn(Optional.empty());

        // Act & Assert
        NoSuchElementException exception = assertThrows(
                NoSuchElementException.class,
                () -> geoPolygonService.getPolygonByUuid(testUuid)
        );

        assertEquals("Polígono con UUID " + testUuid + " no encontrado.", exception.getMessage());
        Mockito.verify(geoPolygonRepository, Mockito.times(1)).findByUuidGpAndActiveTrue(testUuid);
    }



}
