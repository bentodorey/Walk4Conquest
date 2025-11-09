

DROP DATABASE IF EXISTS conquistas_app;
CREATE DATABASE conquistas_app;
USE conquistas_app;


CREATE TABLE Utilizador (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
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
