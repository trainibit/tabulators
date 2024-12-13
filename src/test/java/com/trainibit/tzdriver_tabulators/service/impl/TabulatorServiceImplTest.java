package com.trainibit.tzdriver_tabulators.service.impl;

import com.trainibit.tzdriver_tabulators.entity.Tabulator;
import com.trainibit.tzdriver_tabulators.mapper.TabulatorMapper;
import com.trainibit.tzdriver_tabulators.repository.TabulatorRepository;
import com.trainibit.tzdriver_tabulators.response.TabulatorResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class TabulatorServiceImplTest {
    @Mock
    private TabulatorRepository tabulatorRepository;

    @Mock
    private TabulatorMapper tabulatorMapper;

    @InjectMocks
    private TabulatorServiceImpl tabulatorService;

    @Test
    void getAllTabulators_ShouldReturnAllTabulators_WhenActiveTabulatorsExist() {

        // Arrange
        Tabulator tabulator1 = new Tabulator();
        tabulator1.setUuidTab(UUID.randomUUID());
        Tabulator tabulator2 = new Tabulator();
        tabulator2.setUuidTab(UUID.randomUUID());

        List<Tabulator> activeTabulators = List.of(tabulator1, tabulator2);

        TabulatorResponse response1 = new TabulatorResponse();
        response1.setUuidTab(tabulator1.getUuidTab());
        TabulatorResponse response2 = new TabulatorResponse();
        response2.setUuidTab(tabulator2.getUuidTab());

        Mockito.when(tabulatorRepository.findAllByActiveTrue())
                .thenReturn(activeTabulators);

        Mockito.when(tabulatorMapper.mapEntityToDto(tabulator1))
                .thenReturn(response1);

        Mockito.when(tabulatorMapper.mapEntityToDto(tabulator2))
                .thenReturn(response2);

        // Act
        List<TabulatorResponse> result = tabulatorService.getAllActiveTabulators();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals(tabulator1.getUuidTab(), result.get(0).getUuidTab());
        assertEquals(tabulator2.getUuidTab(), result.get(1).getUuidTab());

        Mockito.verify(tabulatorRepository, Mockito.times(1)).findAllByActiveTrue();
        Mockito.verify(tabulatorMapper, Mockito.times(1)).mapEntityToDto(tabulator1);
        Mockito.verify(tabulatorMapper, Mockito.times(1)).mapEntityToDto(tabulator2);
    }

    @Test
    void getAllActiveTabulators_ShouldReturnEmptyList_WhenNoActiveTabulatorsExist() {
        // Arrange
        Mockito.when(tabulatorRepository.findAllByActiveTrue())
                .thenReturn(new ArrayList<>());

        // Act
        List<TabulatorResponse> result = tabulatorService.getAllActiveTabulators();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        Mockito.verify(tabulatorRepository, Mockito.times(1)).findAllByActiveTrue();
        Mockito.verifyNoInteractions(tabulatorMapper);
    }

    @Test
    void getTabulatorByUuid_ShouldReturnTabulator_WhenTabulatorExists() {
        // Arrange
        UUID uuidTab = UUID.randomUUID();
        Tabulator tabulator = new Tabulator();
        tabulator.setUuidTab(uuidTab);

        TabulatorResponse response = new TabulatorResponse();
        response.setUuidTab(uuidTab);

        Mockito.when(tabulatorRepository.findByUuidTabAndActiveTrue(uuidTab))
                .thenReturn(Optional.of(tabulator));

        Mockito.when(tabulatorMapper.mapEntityToDto(tabulator))
                .thenReturn(response);

        // Act
        TabulatorResponse result = tabulatorService.getTabulatorByUuid(uuidTab);

        // Assert
        assertNotNull(result);
        assertEquals(uuidTab, result.getUuidTab());

        Mockito.verify(tabulatorRepository, Mockito.times(1)).findByUuidTabAndActiveTrue(uuidTab);
        Mockito.verify(tabulatorMapper, Mockito.times(1)).mapEntityToDto(tabulator);
    }

    @Test
    void getTabulatorByUuid_ShouldThrowException_WhenTabulatorDoesNotExist() {
        // Arrange
        UUID uuidTab = UUID.randomUUID();

        Mockito.when(tabulatorRepository.findByUuidTabAndActiveTrue(uuidTab))
                .thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tabulatorService.getTabulatorByUuid(uuidTab);
        });

        assertEquals("Tabulador con UUID: " + uuidTab + " no encontrado.", exception.getMessage());
        Mockito.verify(tabulatorRepository, Mockito.times(1)).findByUuidTabAndActiveTrue(uuidTab);
        Mockito.verifyNoInteractions(tabulatorMapper);
    }
}
