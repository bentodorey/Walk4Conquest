package com.walk4conquest.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.walk4conquest.Entidades.Percurso;
import com.walk4conquest.Entidades.Utilizador;
import java.util.List;

public interface PercursoRepository extends JpaRepository<Percurso, Long> {
    
    // Buscar todos os percursos de um utilizador
    List<Percurso> findByUtilizadorOrderByDataInicioDesc(Utilizador utilizador);
    
    // Buscar percursos por estado
    List<Percurso> findByEstado(Percurso.EstadoPercurso estado);
    
    // Buscar percursos em andamento de um utilizador
    List<Percurso> findByUtilizadorAndEstado(Utilizador utilizador, Percurso.EstadoPercurso estado);
}