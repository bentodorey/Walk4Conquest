package com.walk4conquest.dto;

public class IniciarPercursoRequest {
    private Long utilizadorId;
    private Double latitudeInicial;
    private Double longitudeInicial;

    // CONSTRUTORES
    public IniciarPercursoRequest() {}

    // GETTERS & SETTERS
    public Long getUtilizadorId() {
        return utilizadorId;
    }

    public void setUtilizadorId(Long utilizadorId) {
        this.utilizadorId = utilizadorId;
    }

    public Double getLatitudeInicial() {
        return latitudeInicial;
    }

    public void setLatitudeInicial(Double latitudeInicial) {
        this.latitudeInicial = latitudeInicial;
    }

    public Double getLongitudeInicial() {
        return longitudeInicial;
    }

    public void setLongitudeInicial(Double longitudeInicial) {
        this.longitudeInicial = longitudeInicial;
    }
}