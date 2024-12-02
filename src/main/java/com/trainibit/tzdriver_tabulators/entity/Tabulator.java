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
@Table(name = "tabulator")
public class Tabulator {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tabulator_id_gen")
    @SequenceGenerator(name = "tabulator_id_gen", sequenceName = "tabulator_id_tab_seq", allocationSize = 1)
    @Column(name = "id_tab", nullable = false)
    private Long id;

    @Column(name = "uuid_tab", nullable = false)
    private UUID uuidTab;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "originpolygon_id", nullable = false)
    private GeoPolygon originpolygon;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "destinationpolygon_id", nullable = false)
    private GeoPolygon destinationpolygon;

    @Column(name = "cost_tab", nullable = false)
    private Double costTab;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "registred_tab", nullable = false)
    private Instant registredTab;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_tab", nullable = false)
    private Instant updatedTab;

    @ColumnDefault("true")
    @Column(name = "active_tab", nullable = false)
    private Boolean activeTab = false;

}