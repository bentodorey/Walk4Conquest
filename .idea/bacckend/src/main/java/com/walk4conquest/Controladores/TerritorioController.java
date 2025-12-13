package com.walk4conquest.Controladores;

import com.walk4conquest.Entidades.Territorio;
import com.walk4conquest.Servicos.TerritorioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/territorios")
public class TerritorioController {

    private final TerritorioService territorioService;

    public TerritorioController(TerritorioService territorioService) {
        this.territorioService = territorioService;
    }

    @GetMapping
    public List<Territorio> findAll() {
        return territorioService.findAll();
    }

    @GetMapping("/{id}")
    public Territorio findById(@PathVariable Long id) {
        return territorioService.findById(id);
    }

    @PostMapping
    public Territorio save(@RequestBody Territorio territorio) {
        return territorioService.save(territorio);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        territorioService.delete(id);
    }
}
