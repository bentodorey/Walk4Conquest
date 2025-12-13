package com.walk4conquest.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.walk4conquest.Entidades.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
