package com.walk4conquest.Controladores;

import com.walk4conquest.Entidades.Publicacao;
import com.walk4conquest.Servicos.PublicacaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publicacoes")
public class PublicacaoController {

    private final PublicacaoService publicacaoService;

    public PublicacaoController(PublicacaoService publicacaoService) {
        this.publicacaoService = publicacaoService;
    }

    @GetMapping
    public List<Publicacao> findAll() {
        return publicacaoService.findAll();
    }

    @GetMapping("/{id}")
    public Publicacao findById(@PathVariable Long id) {
        return publicacaoService.findById(id);
    }

    @PostMapping
    public Publicacao save(@RequestBody Publicacao publicacao) {
        return publicacaoService.save(publicacao);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        publicacaoService.delete(id);
    }
}
