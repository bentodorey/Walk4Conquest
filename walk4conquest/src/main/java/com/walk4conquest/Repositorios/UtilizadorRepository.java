package com.walk4conquest.Repositorios;

import com.walk4conquest.Entidades.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilizadorRepository extends JpaRepository<Utilizador, Long> {
    boolean existsByEmail(String email);
    Utilizador findByEmail(String email);
}
