

USE conquistas_app;


INSERT INTO Utilizador (nome, username, email, password_hash, sexo, altura_cm, peso_kg, data_nascimento, pontos)
VALUES
('João Silva', 'joaosilva', 'joao@gmail.com', '12345', 'Masculino', 178.00, 72.50, '1998-02-14', 100),
('Maria Costa', 'mariac', 'maria@gmail.com', 'abcde', 'Feminino', 165.00, 58.00, '2000-08-09', 150),
('Tiago Ramos', 'tiagor', 'tiago@gmail.com', 'qwerty', 'Masculino', 182.00, 80.00, '1996-05-20', 200);


INSERT INTO Territorio (nome, descricao, latitude, longitude)
VALUES
('Lisboa Centro', 'Área central da cidade de Lisboa', 38.7169, -9.1399),
('Parque das Nações', 'Zona moderna junto ao rio Tejo', 38.7686, -9.0945);


INSERT INTO Percurso (utilizador_id, territorio_id, data_inicio, data_fim, distancia_km, duracao_min)
VALUES
(1, 1, '2025-10-30 10:00:00', '2025-10-30 10:45:00', 5.2, 45),
(2, 2, '2025-10-31 09:00:00', '2025-10-31 09:30:00', 3.0, 30);


INSERT INTO CoordenadaPercurso (percurso_id, latitude, longitude)
VALUES
(1, 38.7160, -9.1400),
(1, 38.7170, -9.1380),
(2, 38.7690, -9.0950),
(2, 38.7700, -9.0930);


INSERT INTO Conquista (utilizador_id, territorio_id, distancia_km, duracao_min)
VALUES
(1, 1, 5.2, 45),
(2, 2, 3.0, 30);


INSERT INTO Publicacao (utilizador_id, percurso_id, visibilidade, descricao)
VALUES
(1, 1, 'publico', 'Primeira conquista em Lisboa Centro!'),
(2, 2, 'amigos', 'Conquista concluída no Parque das Nações!');

INSERT INTO Comentario (publicacao_id, utilizador_id, texto)
VALUES
(1, 2, 'Boa, João! Grande percurso!'),
(2, 1, 'Parabéns Maria! Belo trajeto.');
