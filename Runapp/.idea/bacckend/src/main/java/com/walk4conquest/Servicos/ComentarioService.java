package com.walk4conquest.Servicos;

import java.util.List;
import org.springframework.stereotype.Service;

import com.walk4conquest.Entidades.Comentario;
import com.walk4conquest.Repositorios.ComentarioRepository;

@Service
public class ComentarioService {

    private final ComentarioRepository repo;

    public ComentarioService(ComentarioRepository repo) {
        this.repo = repo;
    }

    public List<Comentario> findAll() { return repo.findAll(); }

    public Comentario findById(Long id) { return repo.findById(id).orElse(null); }

    public Comentario save(Comentario c) { return repo.save(c); }

    public void delete(Long id) { repo.deleteById(id); }


}
