package com.walk4conquest.Entidades;

import jakarta.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "utilizador")
public class Utilizador implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    

private String sexo;

@Column(name = "altura_cm")
private Double alturaCm;

@Column(name = "peso_kg")
private Double pesoKg;

@Column(name = "data_nascimento")
private LocalDate dataNascimento;

   
    @Column(name = "pontos")
    private Integer pontos = 0;

    @Column(name = "total_distancia_km")
    private Double totalDistanciaKm = 0.0;

    @Column(name = "total_corridas")
    private Integer totalCorridas = 0;

    @Column(name = "total_territorios_conquistados")
    private Integer totalTerritoriosConquistados = 0;

    @Column(name = "nivel")
    private Integer nivel = 1;

    @Column(name = "experiencia")
    private Integer experiencia = 0;

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Double getAlturaCm() {
        return alturaCm;
    }

    public void setAlturaCm(Double alturaCm) {
        this.alturaCm = alturaCm;
    }

    public Double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(Double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public Double getTotalDistanciaKm() {
        return totalDistanciaKm;
    }

    public void setTotalDistanciaKm(Double totalDistanciaKm) {
        this.totalDistanciaKm = totalDistanciaKm;
    }

    public Integer getTotalCorridas() {
        return totalCorridas;
    }

    public void setTotalCorridas(Integer totalCorridas) {
        this.totalCorridas = totalCorridas;
    }

    public Integer getTotalTerritoriosConquistados() {
        return totalTerritoriosConquistados;
    }

    public void setTotalTerritoriosConquistados(Integer totalTerritoriosConquistados) {
        this.totalTerritoriosConquistados = totalTerritoriosConquistados;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Integer experiencia) {
        this.experiencia = experiencia;
    }

    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}