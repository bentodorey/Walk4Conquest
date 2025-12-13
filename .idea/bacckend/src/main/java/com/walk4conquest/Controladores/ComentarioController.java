package com.walk4conquest.Controladores;

import com.walk4conquest.Entidades.Comentario;
import com.walk4conquest.Servicos.ComentarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @GetMapping
    public List<Comentario> findall() {
        return comentarioService.findAll();
    }

    @GetMapping("/{id}")
    public Comentario findById(@PathVariable Long id) {
        return comentarioService.findById(id);
    }

    @PostMapping
    public Comentario save(@RequestBody Comentario comentario) {
        return comentarioService.save(comentario);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        comentarioService.delete(id);
    }
}
