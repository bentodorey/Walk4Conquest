package com.walk4conquest.Servicos;

import com.walk4conquest.Entidades.Utilizador;
import com.walk4conquest.UserRegisterDTO;
import com.walk4conquest.Repositorios.UtilizadorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilizadorService {

    private final UtilizadorRepository repo;
    private final PasswordEncoder passwordEncoder;

    public UtilizadorService(UtilizadorRepository repo, PasswordEncoder passwordEncoder) {
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

        
        if (repo.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já registado.");
        }

        
        if (repo.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username já registado.");
        }

        
        Utilizador u = new Utilizador();
        u.setNome(dto.getNome());
        u.setUsername(dto.getUsername());
        u.setEmail(dto.getEmail());

        
        String hash = passwordEncoder.encode(dto.getPassword());
        u.setPassword(hash);

        
        u.setSexo(dto.getSexo());
        u.setAlturaCm(dto.getAlturaCm());
        u.setPesoKg(dto.getPesoKg());
        u.setDataNascimento(dto.getDataNascimento());

        
        return repo.save(u);
    }

   

   public Utilizador findByEmail(String email) {
    Optional<Utilizador> opt = repo.findByEmail(email);
    if (opt.isPresent()) {
        return opt.get();
    } else {
        throw new RuntimeException();
    }
}

public Utilizador findByUsername(String username) {
    Optional<Utilizador> opt = repo.findByUsername(username);
    if (opt.isPresent()) {
        return opt.get();
    } else {
        throw new RuntimeException();
    }

    }
}