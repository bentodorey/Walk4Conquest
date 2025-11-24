package com.walk4conquest.Servicos;

import java.util.List;
import org.springframework.stereotype.Service;

import com.walk4conquest.Entidades.Percurso;
import com.walk4conquest.Repositorios.PercursoRepository;

@Service
public class PercursoService {

    private final PercursoRepository repo;

    public PercursoService(PercursoRepository repo) {
        this.repo = repo;
    }

    public List<Percurso> findAll() { return repo.findAll(); }

    public Percurso findById(Long id) { return repo.findById(id).orElse(null); }

    public Percurso save(Percurso p) { return repo.save(p); }

    public void delete(Long id) { repo.deleteById(id); }
}
