
DROP DATABASE IF EXISTS conquistas_app;
CREATE DATABASE conquistas_app;
USE conquistas_app;



CREATE TABLE Utilizador (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    sexo ENUM('Masculino', 'Feminino', 'Outro') DEFAULT NULL,
    altura_cm DECIMAL(5,2) DEFAULT NULL,
    peso_kg DECIMAL(5,2) DEFAULT NULL,
    data_nascimento DATE DEFAULT NULL,
    pontos INT DEFAULT 0,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE Territorio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    latitude DECIMAL(10,8),
    longitude DECIMAL(11,8)
);


CREATE TABLE Percurso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    utilizador_id INT NOT NULL,
    territorio_id INT,
    data_inicio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_fim TIMESTAMP NULL,
    distancia_km DECIMAL(6,2),
    duracao_min INT,
    FOREIGN KEY (utilizador_id) REFERENCES Utilizador(id),
    FOREIGN KEY (territorio_id) REFERENCES Territorio(id)
);


CREATE TABLE CoordenadaPercurso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    percurso_id INT NOT NULL,
    latitude DECIMAL(10,8) NOT NULL,
    longitude DECIMAL(11,8) NOT NULL,
    timestamp_ponto TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (percurso_id) REFERENCES Percurso(id)
);


CREATE TABLE Conquista (
    id INT AUTO_INCREMENT PRIMARY KEY,
    utilizador_id INT NOT NULL,
    territorio_id INT NOT NULL,
    data_conquista TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    distancia_km DECIMAL(6,2),
    duracao_min INT,
    FOREIGN KEY (utilizador_id) REFERENCES Utilizador(id),
    FOREIGN KEY (territorio_id) REFERENCES Territorio(id)
);


CREATE TABLE Publicacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    utilizador_id INT NOT NULL,
    percurso_id INT NOT NULL,
    visibilidade ENUM('publico', 'amigos', 'grupo') DEFAULT 'publico',
    descricao TEXT,
    data_publicacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (utilizador_id) REFERENCES Utilizador(id),
    FOREIGN KEY (percurso_id) REFERENCES Percurso(id)
);

CREATE TABLE Comentario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    publicacao_id INT NOT NULL,
    utilizador_id INT NOT NULL,
    texto TEXT NOT NULL,
    data_comentario TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (publicacao_id) REFERENCES Publicacao(id),
    FOREIGN KEY (utilizador_id) REFERENCES Utilizador(id)
);



CREATE VIEW RankingUtilizadores AS
SELECT 
    u.id,
    u.nome,
    u.pontos,
    COUNT(c.id) AS conquistas,
    SUM(c.distancia_km) AS total_km
FROM Utilizador u
LEFT JOIN Conquista c ON u.id = c.utilizador_id
GROUP BY u.id, u.nome, u.pontos
ORDER BY u.pontos DESC;


DELIMITER //
CREATE TRIGGER atualizar_pontos_apos_conquista
AFTER INSERT ON Conquista
FOR EACH ROW
BEGIN
    UPDATE Utilizador
    SET pontos = pontos + 10
    WHERE id = NEW.utilizador_id;
END //
DELIMITER ;


-- Para criar utilizadores reais, use a API POST /auth/register

INSERT INTO Utilizador (nome, username, email, password, sexo, altura_cm, peso_kg, data_nascimento, pontos)
VALUES
('João Silva', 'joaosilva', 'joao@gmail.com', '$2a$10$dummyhash1', 'Masculino', 178.00, 72.50, '1998-02-14', 100),
('Maria Costa', 'mariac', 'maria@gmail.com', '$2a$10$dummyhash2', 'Feminino', 165.00, 58.00, '2000-08-09', 150),
('Tiago Ramos', 'tiagor', 'tiago@gmail.com', '$2a$10$dummyhash3', 'Masculino', 182.00, 80.00, '1996-05-20', 200);


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



SELECT 'Base de dados criada com sucesso!' AS status;
SELECT 'Tabelas criadas:' AS info;
SHOW TABLES;
SELECT 'Estrutura da tabela Utilizador:' AS info;
DESCRIBE Utilizador;