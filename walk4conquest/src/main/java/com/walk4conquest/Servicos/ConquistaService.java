package com.walk4conquest.Servicos;

import java.util.List;
import org.springframework.stereotype.Service;

import com.walk4conquest.Entidades.Conquista;
import com.walk4conquest.Repositorios.ConquistaRepository;

@Service
public class ConquistaService {

    private final ConquistaRepository repo;

    public ConquistaService(ConquistaRepository repo) {
        this.repo = repo;
    }

    public List<Conquista> findAll() { return repo.findAll(); }

    public Conquista findById(Long id) { return repo.findById(id).orElse(null); }

    public Conquista save(Conquista c) { return repo.save(c); }

    public void delete(Long id) { repo.deleteById(id); }
}
