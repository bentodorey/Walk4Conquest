package com.walk4conquest.dto;

import com.walk4conquest.Entidades.Percurso;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PercursoResponse {
    private Long id;
    private Long utilizadorId;
    private String utilizadorNome;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Double distanciaKm;
    private Integer duracaoMin;
    private Integer calorias;
    private Integer passos;
    private String ritmoMedio;
    private Double velocidadeMediaKmh;
    private String estado;
    private List<CoordenadaDTO> coordenadas;

    // CONSTRUTOR que converte Percurso em Response
    public PercursoResponse(Percurso percurso) {
        this.id = percurso.getId();
        this.utilizadorId = percurso.getUtilizador().getId();
        this.utilizadorNome = percurso.getUtilizador().getNome();
        this.dataInicio = percurso.getDataInicio();
        this.dataFim = percurso.getDataFim();
        this.distanciaKm = percurso.getDistanciaKm();
        this.duracaoMin = percurso.getDuracaoMin();
        this.calorias = percurso.getCalorias();
        this.passos = percurso.getPassos();
        this.ritmoMedio = percurso.getRitmoMedio();
        this.velocidadeMediaKmh = percurso.getVelocidadeMediaKmh();
        this.estado = percurso.getEstado().name();
        this.coordenadas = percurso.getCoordenadas().stream()
            .map(c -> new CoordenadaDTO(c.getLatitude(), c.getLongitude()))
            .collect(Collectors.toList());
    }

    // DTO interno para coordenadas
    public static class CoordenadaDTO {
        private Double latitude;
        private Double longitude;

        public CoordenadaDTO(Double latitude, Double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public Double getLatitude() { return latitude; }
        public void setLatitude(Double latitude) { this.latitude = latitude; }
        public Double getLongitude() { return longitude; }
        public void setLongitude(Double longitude) { this.longitude = longitude; }
    }

    // GETTERS (sem setters, é só para response)
    public Long getId() { return id; }
    public Long getUtilizadorId() { return utilizadorId; }
    public String getUtilizadorNome() { return utilizadorNome; }
    public LocalDateTime getDataInicio() { return dataInicio; }
    public LocalDateTime getDataFim() { return dataFim; }
    public Double getDistanciaKm() { return distanciaKm; }
    public Integer getDuracaoMin() { return duracaoMin; }
    public Integer getCalorias() { return calorias; }
    public Integer getPassos() { return passos; }
    public String getRitmoMedio() { return ritmoMedio; }
    public Double getVelocidadeMediaKmh() { return velocidadeMediaKmh; }
    public String getEstado() { return estado; }
    public List<CoordenadaDTO> getCoordenadas() { return coordenadas; }
}