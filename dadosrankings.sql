

USE conquistas_app;


SELECT nome, username, pontos 
FROM Utilizador 
ORDER BY pontos DESC;


SELECT u.nome, t.nome AS territorio, c.data_conquista, c.distancia_km, c.duracao_min
FROM Conquista c
JOIN Utilizador u ON c.utilizador_id = u.id
JOIN Territorio t ON c.territorio_id = t.id
WHERE u.username = 'joaosilva';


SELECT * FROM RankingUtilizadores;


SELECT p.id, u.username, p.descricao, c.texto AS comentario, c.data_comentario
FROM Publicacao p
LEFT JOIN Comentario c ON p.id = c.publicacao_id
JOIN Utilizador u ON p.utilizador_id = u.id;


SELECT latitude, longitude, timestamp_ponto
FROM CoordenadaPercurso
WHERE percurso_id = 1
ORDER BY timestamp_ponto ASC;
