package com.walk4conquest.Controladores;

import com.walk4conquest.Entidades.Percurso;
import com.walk4conquest.Servicos.PercursoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/percurso")
public class PercursoController {

    private final PercursoService percursoService;

    public PercursoController(PercursoService percursoService) {
        this.percursoService = percursoService;
    }

    @GetMapping
    public List<Percurso> findAll() {
        return percursoService.findAll();
    }

    @GetMapping("/{id}")
    public Percurso findById(@PathVariable Long id) {
        return percursoService.findById(id);
    }

    @PostMapping
    public Percurso save(@RequestBody Percurso percurso) {
        return percursoService.save(percurso);
    }

    

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        percursoService.delete(id);
    }
}
