package com.walk4conquest.Controladores;

import com.walk4conquest.Entidades.Percurso;
import com.walk4conquest.Servicos.PercursoService;
import com.walk4conquest.dto.IniciarPercursoRequest;
import com.walk4conquest.dto.CoordenadaRequest;
import com.walk4conquest.dto.FinalizarPercursoRequest;
import com.walk4conquest.dto.PercursoResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/percurso")
public class PercursoController {

    private final PercursoService percursoService;

    public PercursoController(PercursoService percursoService) {
        this.percursoService = percursoService;
    }

    // ENDPOINTS ANTIGOS (manter para compatibilidade)

    @GetMapping
    public List<Percurso> findAll() {
        return percursoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Percurso percurso = percursoService.findById(id);
            return ResponseEntity.ok(new PercursoResponse(percurso));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping
    public Percurso save(@RequestBody Percurso percurso) {
        return percursoService.save(percurso);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        percursoService.delete(id);
    }

    // NOVOS ENDPOINTS PARA O SISTEMA DE CORRIDA

    /**
     * POST /percurso/iniciar
     * Iniciar uma nova corrida
     */
    @PostMapping("/iniciar")
    public ResponseEntity<?> iniciarPercurso(@RequestBody IniciarPercursoRequest request) {
        try {
            Percurso percurso = percursoService.iniciarPercurso(
                request.getUtilizadorId(),
                request.getLatitudeInicial(),
                request.getLongitudeInicial()
            );

            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new PercursoResponse(percurso));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * POST /percurso/{id}/adicionar-ponto
     * Adicionar coordenada GPS durante a corrida
     */
    @PostMapping("/{id}/adicionar-ponto")
    public ResponseEntity<?> adicionarPonto(@PathVariable Long id, 
                                           @RequestBody CoordenadaRequest coordenada) {
        try {
            Percurso percurso = percursoService.adicionarCoordenada(
                id,
                coordenada.getLatitude(),
                coordenada.getLongitude()
            );

            return ResponseEntity.ok(Map.of(
                "message", "Coordenada adicionada com sucesso",
                "totalCoordenadas", percurso.getCoordenadas().size()
            ));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * POST /percurso/{id}/finalizar
     * Finalizar corrida e guardar estatísticas
     */
    @PostMapping("/{id}/finalizar")
    public ResponseEntity<?> finalizarPercurso(@PathVariable Long id,
                                               @RequestBody FinalizarPercursoRequest request) {
        try {
            Percurso percurso = percursoService.finalizarPercurso(
                id,
                request.getDistanciaKm(),
                request.getDuracaoMin(),
                request.getCalorias(),
                request.getPassos(),
                request.getRitmoMedio(),
                request.getVelocidadeMediaKmh()
            );

            return ResponseEntity.ok(new PercursoResponse(percurso));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * GET /percurso/historico
     * Ver histórico de corridas do utilizador autenticado
     */
    @GetMapping("/historico")
    public ResponseEntity<?> getHistorico(@RequestParam Long utilizadorId) {
        try {
            List<Percurso> percursos = percursoService.getHistoricoUtilizador(utilizadorId);
            
            List<PercursoResponse> response = percursos.stream()
                .map(PercursoResponse::new)
                .collect(Collectors.toList());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * GET /percurso/em-andamento
     * Verificar se utilizador tem corrida em andamento
     */
    @GetMapping("/em-andamento")
    public ResponseEntity<?> getPercursoEmAndamento(@RequestParam Long utilizadorId) {
        try {
            Percurso percurso = percursoService.getPercursoEmAndamento(utilizadorId);
            
            if (percurso == null) {
                return ResponseEntity.ok(Map.of("message", "Nenhum percurso em andamento"));
            }

            return ResponseEntity.ok(new PercursoResponse(percurso));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", e.getMessage()));
        }
    }
}