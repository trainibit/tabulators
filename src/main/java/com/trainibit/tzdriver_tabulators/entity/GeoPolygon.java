package com.trainibit.tzdriver_tabulators.entity;

import jakarta.persistence.*;
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

    @Column(name = "registred_gp", nullable = false, updatable = false, insertable = false)
    private Instant registredGp;

    @Column(name = "updated_gp", nullable = false, insertable = false)
    private Instant updatedGp;

    @ColumnDefault("true")
    @Column(name = "active_gp", nullable = false, insertable = false)
    private Boolean isActive;

    @OneToMany(mappedBy = "geoPolygon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PolygonVertex> polygonVertex = new ArrayList<>();

}