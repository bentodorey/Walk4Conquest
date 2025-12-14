package com.walk4conquest.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/leaderboard")
@CrossOrigin(origins = "*")
public class LeaderboardController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Map<String, Object>> getLeaderboard() {
        String sql = "SELECT " +
                     "id, " +
                     "nome, " +
                     "username, " +
                     "pontos, " +
                     "total_distancia_km, " +
                     "total_corridas, " +
                     "total_territorios_conquistados, " +
                     "nivel, " +
                     "conquistas " +
                     "FROM ranking_utilizadores " +
                     "ORDER BY pontos DESC, total_distancia_km DESC " +
                     "LIMIT 100";
        return jdbcTemplate.queryForList(sql);
    }
}