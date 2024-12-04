package com.trainibit.tzdriver_tabulators.mapper;

import com.trainibit.tzdriver_tabulators.entity.GeoPolygon;
import com.trainibit.tzdriver_tabulators.response.GeoPolygonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface GeoPolygonMapper2 {
    @Mappings({
            @Mapping(source = "uuidGp", target = "uuidGp"),
            @Mapping(source = "zoneGp", target = "zoneGp")

    })
    GeoPolygonResponse mapEntityToDto(GeoPolygon geoPolygon);
}
