package com.trainibit.tzdriver_tabulators.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "geo_polygon")
public class GeoPolygon {
    @Id
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "geo_polygon_id_gen")
    @SequenceGenerator(name = "geo_polygon_id_gen", sequenceName = "geo_polygon_id_gp_seq", allocationSize = 1)*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gp", nullable = false)
    private Long id;

    @Column(name = "uuid_gp", nullable = false)
    private UUID uuidGp;

    @Column(name = "zone_gp", nullable = false, length = Integer.MAX_VALUE)
    private String zoneGp;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "registred_gp", nullable = false)
    private Instant registredGp;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_gp", nullable = false)
    private Instant updatedGp;

    @ColumnDefault("true")
    @Column(name = "active_gp", nullable = false)
    private Boolean activeGp = false;

    @OneToMany(mappedBy = "geoPolygon")
    private List<PolygonVertex> polygonVertices = new ArrayList<>();

}