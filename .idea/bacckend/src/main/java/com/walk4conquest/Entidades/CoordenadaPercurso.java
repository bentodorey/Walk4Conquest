package com.walk4conquest.Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "CoordenadaPercurso")
public class CoordenadaPercurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "percurso_id")
    private Percurso percurso;

    private Double latitude;

    private Double longitude;

    @Column(name = "timestamp_ponto")
    private String timestampPonto;

    // GETTERS & SETTERS
}

