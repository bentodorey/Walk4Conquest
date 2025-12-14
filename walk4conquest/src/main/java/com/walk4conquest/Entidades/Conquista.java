package com.walk4conquest.Entidades;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "conquista")
public class Conquista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utilizador_id")
    private Utilizador utilizador;

    @ManyToOne
    @JoinColumn(name = "territorio_id")
    private Territorio territorio;

    @Column(name = "data_conquista")
    private String dataConquista;

    @Column(name = "distancia_km")
    private Double distanciaKm;

    @Column(name = "duracao_min")
    private Integer duracaoMin;

    
}

