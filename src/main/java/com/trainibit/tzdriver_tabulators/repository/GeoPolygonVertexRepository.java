package com.trainibit.tzdriver_tabulators.repository;

import com.trainibit.tzdriver_tabulators.entity.GeoPolygon;
import com.trainibit.tzdriver_tabulators.entity.GeoPolygonVertex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeoPolygonVertexRepository extends JpaRepository<GeoPolygonVertex, Long> {

}
