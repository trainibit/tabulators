package com.trainibit.tzdriver_tabulators.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
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

    @Column(name = "uuid_gp", nullable = false, unique = true)
    private UUID uuidGp;

    @Column(name = "zone_gp", nullable = false, length = Integer.MAX_VALUE)
    private String zoneGp;

    @Column(name = "registred_gp", nullable = false, updatable = false, insertable = false)
    private Timestamp registredGp;

    @Column(name = "updated_gp", nullable = false, insertable = false)
    private Timestamp updatedGp;

    @ColumnDefault("true")
    @Column(name = "active_gp", nullable = false, insertable = false)
    private Boolean active;

    @OneToMany(mappedBy = "geoPolygon", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<GeoPolygonVertex> polygonVertex = new ArrayList<>();

}