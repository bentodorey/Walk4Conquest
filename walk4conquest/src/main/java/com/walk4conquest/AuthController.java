package com.walk4conquest;

import com.walk4conquest.Entidades.Utilizador;
import com.walk4conquest.UserRegisterDTO;
import com.walk4conquest.Servicos.UtilizadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UtilizadorService service;

    public AuthController(UtilizadorService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO dto) {
        try {
            Utilizador u = service.registar(dto);
            u.setPassword(null);
            return ResponseEntity.status(HttpStatus.CREATED).body(u);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(java.util.Map.of("error", e.getMessage()));
        }
    }

    
}
