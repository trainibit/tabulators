package com.trainibit.tzdriver_tabulators.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "polygon_vertex")
public class PolygonVertex {
    @Id
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "polygon_vertices_id_gen")
    @SequenceGenerator(name = "polygon_vertices_id_gen", sequenceName = "polygon_vertices_id_pv_seq", allocationSize = 1)*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pv", nullable = false)
    private Long id;

    @Column(name = "uuid_pv", nullable = false)
    private UUID uuidPv;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "geo_polygon_id", nullable = false)
    private GeoPolygon geoPolygon;

    @Column(name = "orden_pv", nullable = false)
    private Long ordenPv;

    @Column(name = "latitude_pv", nullable = false)
    private Double latitudePv;

    @Column(name = "length_pv", nullable = false)
    private Double lengthPv;

    @Column(name = "registred_pv", nullable = false, insertable = false, updatable = false)
    private Instant registredPv;

    @Column(name = "updated_pv", nullable = false, insertable = false)
    private Instant updatedPv;

    @ColumnDefault("true")
    @Column(name = "active_pv", nullable = false, insertable = false)
    private Boolean isActive;
}