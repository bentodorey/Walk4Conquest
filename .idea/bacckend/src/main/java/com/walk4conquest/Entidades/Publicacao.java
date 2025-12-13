package com.walk4conquest.Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "Publicacao")
public class Publicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utilizador_id")
    private Utilizador utilizador;

    @ManyToOne
    @JoinColumn(name = "percurso_id")
    private Percurso percurso;

    private String visibilidade;

    private String descricao;

    @Column(name = "data_publicacao")
    private String dataPublicacao;

    // GETTERS & SETTERS
}

