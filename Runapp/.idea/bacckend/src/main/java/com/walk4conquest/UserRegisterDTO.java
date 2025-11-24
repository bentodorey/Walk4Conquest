package com.walk4conquest;

import java.time.LocalDate;

public class UserRegisterDTO {

    private String nome;
    private String username;
    private String email;
    private String password;

    private String sexo;
    private Double alturaCm;
    private Double pesoKg;
    private LocalDate dataNascimento;

    public UserRegisterDTO() {
    }

    // GETTERS
    public String getNome() {
        return nome;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSexo() {
        return sexo;
    }

    public Double getAlturaCm() {
        return alturaCm;
    }

    public Double getPesoKg() {
        return pesoKg;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    // SETTERS
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setAlturaCm(Double alturaCm) {
        this.alturaCm = alturaCm;
    }

    public void setPesoKg(Double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}


