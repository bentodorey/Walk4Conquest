package com.walk4conquest.Servicos;

import java.util.List;
import org.springframework.stereotype.Service;

import com.walk4conquest.Entidades.Publicacao;
import com.walk4conquest.Repositorios.PublicacaoRepository;

@Service
public class PublicacaoService {

    private final PublicacaoRepository repo;

    public PublicacaoService(PublicacaoRepository repo) {
        this.repo = repo;
    }

    public List<Publicacao> findAll() { return repo.findAll(); }

    public Publicacao findById(Long id) { return repo.findById(id).orElse(null); }

    public Publicacao save(Publicacao p) { return repo.save(p); }

    public void delete(Long id) { repo.deleteById(id); }
}
