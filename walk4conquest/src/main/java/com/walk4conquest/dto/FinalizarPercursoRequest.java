package com.walk4conquest.dto;

public class FinalizarPercursoRequest {
    private Double distanciaKm;
    private Integer duracaoMin;
    private Integer calorias;
    private Integer passos;
    private String ritmoMedio;
    private Double velocidadeMediaKmh;

    // CONSTRUTORES
    public FinalizarPercursoRequest() {}

    // GETTERS & SETTERS
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
}