package com.trainibit.tzdriver_tabulators.repository;

import com.trainibit.tzdriver_tabulators.entity.GeoPolygon;
import com.trainibit.tzdriver_tabulators.entity.Tabulator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TabulatorRepository extends JpaRepository<Tabulator, Long> {
    List<Tabulator> findAllByActiveTrue();
    Optional<Tabulator> findByUuidTabAndActiveTrue(UUID uuid);
    boolean existsByOriginpolygonAndDestinationpolygonAndActiveTrueAndUuidTabNot(GeoPolygon originpolygon, GeoPolygon destinationpolygon, UUID uuidTab);


}
