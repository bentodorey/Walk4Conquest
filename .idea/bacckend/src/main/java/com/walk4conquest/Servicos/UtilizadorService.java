package com.walk4conquest.Servicos;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.walk4conquest.UserRegisterDTO;
import com.walk4conquest.Entidades.Utilizador;
import com.walk4conquest.Repositorios.UtilizadorRepository;

@Service
public class UtilizadorService {

    private final UtilizadorRepository repo;
    private final PasswordEncoder passwordEncoder;
    public UtilizadorService(UtilizadorRepository repo,PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Utilizador> findAll() {
        return repo.findAll();
    }

    public Utilizador findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Utilizador save(Utilizador u) {
        return repo.save(u);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
    public Utilizador registar(UserRegisterDTO dto) {

    // 1) Verificar se o email já existe
    if (repo.existsByEmail(dto.getEmail())) {
        throw new RuntimeException("Email já registado.");
    }

    // 2) Criar novo utilizador
    Utilizador u = new Utilizador();
    u.setNome(dto.getNome());
    u.setUsername(dto.getUsername());
    u.setEmail(dto.getEmail());

    // 3) Encriptar password
    String hash = passwordEncoder.encode(dto.getPassword());
    u.setPasswordHash(hash);

    // Definir outros campos opcionais se existir no DTO
    u.setSexo(dto.getSexo());
    u.setAlturaCm(dto.getAlturaCm());
    u.setPesoKg(dto.getPesoKg());
    u.setDataNascimento(dto.getDataNascimento());

    // 4) Guardar na base de dados
    return repo.save(u);
}

}
