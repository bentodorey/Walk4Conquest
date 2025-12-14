package com.walk4conquest.Entidades;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "coordenadaPercurso")
public class CoordenadaPercurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "percurso_id", nullable = false)
    private Percurso percurso;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(name = "timestamp_ponto")
    private LocalDateTime timestampPonto;

    
    public CoordenadaPercurso() {}

    public CoordenadaPercurso(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestampPonto = LocalDateTime.now();
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Percurso getPercurso() {
        return percurso;
    }

    public void setPercurso(Percurso percurso) {
        this.percurso = percurso;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getTimestampPonto() {
        return timestampPonto;
    }

    public void setTimestampPonto(LocalDateTime timestampPonto) {
        this.timestampPonto = timestampPonto;
    }
}