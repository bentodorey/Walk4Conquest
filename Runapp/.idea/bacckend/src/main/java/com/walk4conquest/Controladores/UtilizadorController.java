package com.walk4conquest.Controladores;

import com.walk4conquest.UserRegisterDTO;
import com.walk4conquest.Entidades.Utilizador;
import com.walk4conquest.Servicos.UtilizadorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilizadores")
public class UtilizadorController {

    private final UtilizadorService service;

    public UtilizadorController(UtilizadorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Utilizador> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public Utilizador getById(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    public Utilizador create(@RequestBody Utilizador u) { return service.save(u); }

    @PutMapping("/{id}")
    public Utilizador update(@PathVariable Long id, @RequestBody Utilizador u) {
        u.setId(id);
        return service.save(u);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
    
    @PostMapping("/registar")
public Utilizador registar(@RequestBody UserRegisterDTO dto) {
    return service.registar(dto);
}

}
