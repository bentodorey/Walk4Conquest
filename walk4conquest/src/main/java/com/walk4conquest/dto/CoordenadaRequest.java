package com.walk4conquest.dto;

public class CoordenadaRequest {
    private Double latitude;
    private Double longitude;

    // CONSTRUTORES
    public CoordenadaRequest() {}

    public CoordenadaRequest(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // GETTERS & SETTERS
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}