package com.walk4conquest.Servicos;

import com.walk4conquest.Entidades.Utilizador;
import com.walk4conquest.Repositorios.UtilizadorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        // Tenta encontrar por username primeiro
        Utilizador user = utilizadorRepository.findByUsername(usernameOrEmail)
                .orElse(null);

        // Se não encontrar, tenta por email
        if (user == null) {
            user = utilizadorRepository.findByEmail(usernameOrEmail)
                    .orElse(null);
        }

        // Se ainda não encontrar, lança exceção
        if (user == null) {
            throw new UsernameNotFoundException("Utilizador não encontrado: " + usernameOrEmail);
        }

        return user;
    }
}