package com.walk4conquest.Servicos;

import com.walk4conquest.Entidades.Utilizador;
import com.walk4conquest.UserRegisterDTO;
import com.walk4conquest.Repositorios.UtilizadorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    // --------------------------
    // REGISTO DE NOVO UTILIZADOR
    // --------------------------

    public Utilizador registar(UserRegisterDTO dto) {

        // 1) Verificar se o email já existe
        if (repo.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já registado.");
        }

        // 2) Verificar se o username já existe
        if (repo.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username já registado.");
        }

        // 3) Criar novo utilizador
        Utilizador u = new Utilizador();
        u.setNome(dto.getNome());
        u.setUsername(dto.getUsername());  // ADICIONAR USERNAME
        u.setEmail(dto.getEmail());

        // 4) Encriptar password
        String hash = passwordEncoder.encode(dto.getPassword());
        u.setPassword(hash);

        // 5) Preencher campos opcionais
        u.setSexo(dto.getSexo());
        u.setAlturaCm(dto.getAlturaCm());
        u.setPesoKg(dto.getPesoKg());
        u.setDataNascimento(dto.getDataNascimento());

        // 6) Guardar
        return repo.save(u);
    }

    // --------------------------
    // MÉTODOS AUXILIARES
    // --------------------------

    public Utilizador findByEmail(String email) {
        return repo.findByEmail(email);
    }

    public Utilizador findByUsername(String username) {
        return repo.findByUsername(username);
    }
}