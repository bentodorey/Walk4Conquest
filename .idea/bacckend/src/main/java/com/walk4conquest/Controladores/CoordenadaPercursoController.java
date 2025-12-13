package com.walk4conquest.Controladores;

import com.walk4conquest.Entidades.CoordenadaPercurso;
import com.walk4conquest.Servicos.CoordenadaPercursoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coordenadas")
public class CoordenadaPercursoController {

    private final CoordenadaPercursoService coordenadaService;

    public CoordenadaPercursoController(CoordenadaPercursoService coordenadaService) {
        this.coordenadaService = coordenadaService;
    }

    @GetMapping
    public List<CoordenadaPercurso> findAll() {
        return coordenadaService.findAll();
    }

    @GetMapping("/{id}")
    public CoordenadaPercurso findById(@PathVariable Long id) {
        return coordenadaService.findById(id);
    }

    @PostMapping
    public CoordenadaPercurso save(@RequestBody CoordenadaPercurso coordenada) {
        return coordenadaService.save(coordenada);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        coordenadaService.delete(id);
    }
}
