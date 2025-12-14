package com.walk4conquest.Entidades;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "territorio")
public class Territorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private Double latitude;

    private Double longitude;

    
}
