package com.walk4conquest.Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "Territorio")
public class Territorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private Double latitude;

    private Double longitude;

    // GETTERS & SETTERS
}
