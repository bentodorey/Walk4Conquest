package com.walk4conquest.Repositorios;

import com.walk4conquest.Entidades.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilizadorRepository extends JpaRepository<Utilizador, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    
    Optional<Utilizador> findByEmail(String email);
    Optional<Utilizador> findByUsername(String username);
}