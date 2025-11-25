package com.walk4conquest.Servicos;

import java.util.List;
import org.springframework.stereotype.Service;

import com.walk4conquest.Entidades.Territorio;
import com.walk4conquest.Repositorios.TerritorioRepository;

@Service
public class TerritorioService {
    private final TerritorioRepository repo;

    public TerritorioService(TerritorioRepository repo) {
        this.repo = repo;
    }

    public List<Territorio> findAll() { return repo.findAll(); }

    public Territorio findById(Long id) { return repo.findById(id).orElse(null); }

    public Territorio save(Territorio t) { return repo.save(t); }

    public void delete(Long id) { repo.deleteById(id); }
}
