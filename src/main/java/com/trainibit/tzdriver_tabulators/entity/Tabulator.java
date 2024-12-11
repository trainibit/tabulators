package com.trainibit.tzdriver_tabulators.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tabulator")
public class Tabulator {
    @Id
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tabulator_id_gen")
    @SequenceGenerator(name = "tabulator_id_gen", sequenceName = "tabulator_id_tab_seq", allocationSize = 1)*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tab", nullable = false)
    private Long id;

    @Column(name = "uuid_tab", nullable = false, unique = true)
    private UUID uuidTab;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "originpolygon_id", nullable = false)
    private GeoPolygon originpolygon;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "destinationpolygon_id", nullable = false)
    private GeoPolygon destinationpolygon;

    @Column(name = "cost_tab", nullable = false)
    private Double costTab;

    @Column(name = "registred_tab", nullable = false, insertable = false, updatable = false)
    private Timestamp registredTab;

    @Column(name = "updated_tab", nullable = false, insertable = false)
    private Timestamp updatedTab;

    @ColumnDefault("true")
    @Column(name = "active_tab", nullable = false, insertable = false)
    private Boolean active;

}