package com.walk4conquest.Controladores;

import com.walk4conquest.Entidades.Conquista;
import com.walk4conquest.Servicos.ConquistaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conquistas")
public class ConquistaController {

    private final ConquistaService conquistaService;

    public ConquistaController(ConquistaService conquistaService) {
        this.conquistaService = conquistaService;
    }

    @GetMapping
    public List<Conquista> findAll() {
        return conquistaService.findAll();
    }

    @GetMapping("/{id}")
    public Conquista findById(@PathVariable Long id) {
        return conquistaService.findById(id);
    }

    @PostMapping
    public Conquista save(@RequestBody Conquista conquista) {
        return conquistaService.save(conquista);
    }



    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        conquistaService.delete(id);
    }
}
