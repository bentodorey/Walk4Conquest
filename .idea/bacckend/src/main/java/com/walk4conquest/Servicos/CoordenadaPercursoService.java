package com.walk4conquest.Servicos;

import java.util.List;
import org.springframework.stereotype.Service;

import com.walk4conquest.Entidades.CoordenadaPercurso;
import com.walk4conquest.Repositorios.CoordenadaPercursoRepository;

@Service
public class CoordenadaPercursoService {

    private final CoordenadaPercursoRepository repo;

    public CoordenadaPercursoService(CoordenadaPercursoRepository repo) {
        this.repo = repo;
    }

    public List<CoordenadaPercurso> findAll() { return repo.findAll(); }

    public CoordenadaPercurso findById(Long id) { return repo.findById(id).orElse(null); }

    public CoordenadaPercurso save(CoordenadaPercurso c) { return repo.save(c); }

    public void delete(Long id) { repo.deleteById(id); }
}
