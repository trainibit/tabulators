package com.trainibit.tzdriver_tabulators.mapper;

import com.trainibit.tzdriver_tabulators.entity.Tabulator;
import com.trainibit.tzdriver_tabulators.request.TabulatorRequest;
import com.trainibit.tzdriver_tabulators.response.TabulatorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TabulatorMapper {
    // Mapear de DTO a entidad
    @Mapping(target = "originpolygon.uuidGp", source = "originpolygonUuidGp")
    @Mapping(target = "destinationpolygon.uuidGp", source = "destinationpolygonUuidGp")
    Tabulator mapDtoToEntity(TabulatorRequest tabulatorRequest);

    // Mapear de entidad a DTO
    @Mapping(target = "originpolygonUuidGp", source = "originpolygon.uuidGp")  // Mapear UUID de origen
    @Mapping(target = "destinationpolygonUuidGp", source = "destinationpolygon.uuidGp")  // Mapear UUID de destino
    TabulatorResponse mapEntityToDto(Tabulator tabulator);
}
