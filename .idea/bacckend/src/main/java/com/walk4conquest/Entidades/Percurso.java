package com.walk4conquest.Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "Percurso")
public class Percurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utilizador_id")
    private Utilizador utilizador;

    @ManyToOne
    @JoinColumn(name = "territorio_id")
    private Territorio territorio;

    @Column(name = "data_inicio")
    private String dataInicio;

    @Column(name = "data_fim")
    private String dataFim;

    @Column(name = "distancia_km")
    private Double distanciaKm;

    @Column(name = "duracao_min")
    private Integer duracaoMin;

    // GETTERS & SETTERS
}
