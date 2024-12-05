package com.trainibit.tzdriver_tabulators.repository;

import com.trainibit.tzdriver_tabulators.entity.GeoPolygon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GeoPolygonRepository extends JpaRepository<GeoPolygon, Long> {

    @Query("SELECT g FROM GeoPolygon g JOIN FETCH g.polygonVertex v WHERE g.isActive = true AND v.isActive = true")
    List<GeoPolygon> findAllByIsActiveTrue();

    Optional<GeoPolygon> findByUuidGp(UUID uuidGp);
}
