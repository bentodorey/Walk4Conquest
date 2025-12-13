package com.walk4conquest.Entidades;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "percurso")
public class Percurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utilizador_id", nullable = false)
    private Utilizador utilizador;

    @ManyToOne
    @JoinColumn(name = "territorio_id")
    private Territorio territorio;

    @Column(name = "data_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "data_fim")
    private LocalDateTime dataFim;

    @Column(name = "distancia_km")
    private Double distanciaKm;

    @Column(name = "duracao_min")
    private Integer duracaoMin;

    @Column(name = "calorias")
    private Integer calorias;

    @Column(name = "passos")
    private Integer passos;

    @Column(name = "ritmo_medio")
    private String ritmoMedio;

    @Column(name = "velocidade_media_kmh")
    private Double velocidadeMediaKmh;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoPercurso estado = EstadoPercurso.EM_ANDAMENTO;

    @OneToMany(mappedBy = "percurso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CoordenadaPercurso> coordenadas = new ArrayList<>();

    // ENUM
    public enum EstadoPercurso {
        EM_ANDAMENTO,
        CONCLUIDO,
        PAUSADO
    }

    // CONSTRUTORES
    public Percurso() {}

    public Percurso(Utilizador utilizador) {
        this.utilizador = utilizador;
        this.dataInicio = LocalDateTime.now();
        this.estado = EstadoPercurso.EM_ANDAMENTO;
    }

    // GETTERS & SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(Utilizador utilizador) {
        this.utilizador = utilizador;
    }

    public Territorio getTerritorio() {
        return territorio;
    }

    public void setTerritorio(Territorio territorio) {
        this.territorio = territorio;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public Double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(Double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public Integer getDuracaoMin() {
        return duracaoMin;
    }

    public void setDuracaoMin(Integer duracaoMin) {
        this.duracaoMin = duracaoMin;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    public Integer getPassos() {
        return passos;
    }

    public void setPassos(Integer passos) {
        this.passos = passos;
    }

    public String getRitmoMedio() {
        return ritmoMedio;
    }

    public void setRitmoMedio(String ritmoMedio) {
        this.ritmoMedio = ritmoMedio;
    }

    public Double getVelocidadeMediaKmh() {
        return velocidadeMediaKmh;
    }

    public void setVelocidadeMediaKmh(Double velocidadeMediaKmh) {
        this.velocidadeMediaKmh = velocidadeMediaKmh;
    }

    public EstadoPercurso getEstado() {
        return estado;
    }

    public void setEstado(EstadoPercurso estado) {
        this.estado = estado;
    }

    public List<CoordenadaPercurso> getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(List<CoordenadaPercurso> coordenadas) {
        this.coordenadas = coordenadas;
    }

    // MÉTODOS AUXILIARES
    public void adicionarCoordenada(CoordenadaPercurso coordenada) {
        coordenadas.add(coordenada);
        coordenada.setPercurso(this);
    }

    public void finalizar() {
        this.dataFim = LocalDateTime.now();
        this.estado = EstadoPercurso.CONCLUIDO;
    }
}