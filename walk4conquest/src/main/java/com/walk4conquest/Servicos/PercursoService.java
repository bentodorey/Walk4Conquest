package com.walk4conquest.Servicos;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walk4conquest.Entidades.CoordenadaPercurso;
import com.walk4conquest.Entidades.Percurso;
import com.walk4conquest.Entidades.Utilizador;
import com.walk4conquest.Repositorios.PercursoRepository;
import com.walk4conquest.Repositorios.UtilizadorRepository;
import com.walk4conquest.Repositorios.CoordenadaPercursoRepository;

@Service
public class PercursoService {

    private final PercursoRepository percursoRepo;
    private final UtilizadorRepository utilizadorRepo;
    private final CoordenadaPercursoRepository coordenadaRepo;

    public PercursoService(PercursoRepository percursoRepo, 
                          UtilizadorRepository utilizadorRepo,
                          CoordenadaPercursoRepository coordenadaRepo) {
        this.percursoRepo = percursoRepo;
        this.utilizadorRepo = utilizadorRepo;
        this.coordenadaRepo = coordenadaRepo;
    }

    
    public List<Percurso> findAll() { 
        return percursoRepo.findAll(); 
    }

    public Percurso findById(Long id) { 
        return percursoRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Percurso não encontrado")); 
    }

    public Percurso save(Percurso p) { 
        return percursoRepo.save(p); 
    }

    public void delete(Long id) { 
        percursoRepo.deleteById(id); 
    }

    
    
    @Transactional
    public Percurso iniciarPercurso(Long utilizadorId, Double latInicial, Double longInicial) {
        Utilizador utilizador = utilizadorRepo.findById(utilizadorId)
            .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));

        
        Percurso percurso = new Percurso(utilizador);
        percurso = percursoRepo.save(percurso);

        
        CoordenadaPercurso coordenadaInicial = new CoordenadaPercurso(latInicial, longInicial);
        percurso.adicionarCoordenada(coordenadaInicial);
        coordenadaRepo.save(coordenadaInicial);

        return percurso;
    }

    
    @Transactional
    public Percurso adicionarCoordenada(Long percursoId, Double latitude, Double longitude) {
        Percurso percurso = findById(percursoId);

        if (percurso.getEstado() != Percurso.EstadoPercurso.EM_ANDAMENTO) {
            throw new RuntimeException("Percurso não está em andamento");
        }

        CoordenadaPercurso coordenada = new CoordenadaPercurso(latitude, longitude);
        percurso.adicionarCoordenada(coordenada);
        coordenadaRepo.save(coordenada);

        return percurso;
    }

   
    @Transactional
    public Percurso finalizarPercurso(Long percursoId, Double distanciaKm, Integer duracaoMin,
                                     Integer calorias, Integer passos, String ritmoMedio,
                                     Double velocidadeMediaKmh) {
        Percurso percurso = findById(percursoId);

        if (percurso.getEstado() == Percurso.EstadoPercurso.CONCLUIDO) {
            throw new RuntimeException("Percurso já está concluído");
        }

        
        percurso.finalizar();
        percurso.setDistanciaKm(distanciaKm);
        percurso.setDuracaoMin(duracaoMin);
        percurso.setCalorias(calorias);
        percurso.setPassos(passos);
        percurso.setRitmoMedio(ritmoMedio);
        percurso.setVelocidadeMediaKmh(velocidadeMediaKmh);

        
        if (duracaoMin == null && percurso.getDataInicio() != null && percurso.getDataFim() != null) {
            long minutos = ChronoUnit.MINUTES.between(percurso.getDataInicio(), percurso.getDataFim());
            percurso.setDuracaoMin((int) minutos);
        }

        percurso = percursoRepo.save(percurso);

       
        atualizarEstatisticasEPontos(percurso.getUtilizador(), distanciaKm);

        return percurso;
    }

    
    public List<Percurso> getHistoricoUtilizador(Long utilizadorId) {
        Utilizador utilizador = utilizadorRepo.findById(utilizadorId)
            .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));
        
        return percursoRepo.findByUtilizadorOrderByDataInicioDesc(utilizador);
    }

    
    public Percurso getPercursoEmAndamento(Long utilizadorId) {
        Utilizador utilizador = utilizadorRepo.findById(utilizadorId)
            .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));
        
        List<Percurso> percursosEmAndamento = percursoRepo.findByUtilizadorAndEstado(
            utilizador, Percurso.EstadoPercurso.EM_ANDAMENTO
        );

        return percursosEmAndamento.isEmpty() ? null : percursosEmAndamento.get(0);
    }

    
    private void atualizarEstatisticasEPontos(Utilizador utilizador, Double distanciaKm) {
        
        Double distanciaAtual = utilizador.getTotalDistanciaKm() != null ? utilizador.getTotalDistanciaKm() : 0.0;
        utilizador.setTotalDistanciaKm(distanciaAtual + distanciaKm);
        
        
        Integer corridasAtuais = utilizador.getTotalCorridas() != null ? utilizador.getTotalCorridas() : 0;
        utilizador.setTotalCorridas(corridasAtuais + 1);
        
        
        int pontosGanhos = (int) ((distanciaKm / 2.0) * 50);
        int pontosAtuais = utilizador.getPontos() != null ? utilizador.getPontos() : 0;
        utilizador.setPontos(pontosAtuais + pontosGanhos);
        
        
        utilizadorRepo.save(utilizador);
        
        System.out.println("✅ Utilizador " + utilizador.getNome() + " ganhou " + pontosGanhos + " pontos! (Total: " + utilizador.getPontos() + ")");
    }
}