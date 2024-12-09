package com.trainibit.tzdriver_tabulators.repository;

import com.trainibit.tzdriver_tabulators.entity.GeoPolygon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GeoPolygonRepository extends JpaRepository<GeoPolygon, Long> {

    List<GeoPolygon> findAllByActiveTrue();

    Optional<GeoPolygon> findByUuidGpAndActiveTrue(UUID uuidGp);


}
