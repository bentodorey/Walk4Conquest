
DROP DATABASE IF EXISTS conquistas_app;
CREATE DATABASE conquistas_app;
USE conquistas_app;


CREATE TABLE utilizador (
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
    total_distancia_km DECIMAL(10,2) DEFAULT 0,
    total_corridas INT DEFAULT 0,
    total_territorios_conquistados INT DEFAULT 0,
    nivel INT DEFAULT 1,
    experiencia INT DEFAULT 0,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE territorio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    latitude DECIMAL(10,8),
    longitude DECIMAL(11,8),
    poligono_geojson TEXT COMMENT 'Coordenadas que formam o território em formato GeoJSON',
    area_metros_quadrados DOUBLE COMMENT 'Área total do território',
    conquistador_atual_id INT COMMENT 'ID do utilizador que atualmente detém o território',
    CONSTRAINT fk_conquistador FOREIGN KEY (conquistador_atual_id) REFERENCES utilizador(id)
);


CREATE TABLE percurso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    utilizador_id INT NOT NULL,
    territorio_id INT,
    data_inicio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_fim TIMESTAMP NULL,
    distancia_km DECIMAL(6,2),
    duracao_min INT,
    calorias INT COMMENT 'Calorias queimadas durante o percurso',
    passos INT COMMENT 'Número de passos',
    ritmo_medio VARCHAR(10) COMMENT 'Ritmo médio (ex: 5\'20"/km)',
    velocidade_media_kmh DECIMAL(5,2) COMMENT 'Velocidade média em km/h',
    estado ENUM('EM_ANDAMENTO', 'CONCLUIDO', 'PAUSADO') DEFAULT 'EM_ANDAMENTO',
    FOREIGN KEY (utilizador_id) REFERENCES utilizador(id),
    FOREIGN KEY (territorio_id) REFERENCES territorio(id)
);


CREATE TABLE coordenada_percurso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    percurso_id INT NOT NULL,
    latitude DECIMAL(10,8) NOT NULL,
    longitude DECIMAL(11,8) NOT NULL,
    timestamp_ponto TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (percurso_id) REFERENCES percurso(id)
);


CREATE TABLE conquista (
    id INT AUTO_INCREMENT PRIMARY KEY,
    utilizador_id INT NOT NULL,
    territorio_id INT NOT NULL,
    percurso_id INT COMMENT 'Percurso que originou a conquista',
    data_conquista TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    distancia_km DECIMAL(6,2),
    duracao_min INT,
    FOREIGN KEY (utilizador_id) REFERENCES utilizador(id),
    FOREIGN KEY (territorio_id) REFERENCES territorio(id),
    CONSTRAINT fk_conquista_percurso FOREIGN KEY (percurso_id) REFERENCES percurso(id)
);


CREATE TABLE historico_conquista (
    id INT AUTO_INCREMENT PRIMARY KEY,
    territorio_id INT NOT NULL,
    utilizador_anterior_id INT COMMENT 'Quem perdeu o território',
    utilizador_novo_id INT NOT NULL COMMENT 'Quem conquistou',
    percurso_id INT NOT NULL,
    data_conquista TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    area_conquistada_metros DOUBLE,
    FOREIGN KEY (territorio_id) REFERENCES territorio(id),
    FOREIGN KEY (utilizador_anterior_id) REFERENCES utilizador(id),
    FOREIGN KEY (utilizador_novo_id) REFERENCES utilizador(id),
    FOREIGN KEY (percurso_id) REFERENCES percurso(id)
);


CREATE TABLE publicacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    utilizador_id INT NOT NULL,
    percurso_id INT NOT NULL,
    visibilidade ENUM('publico', 'amigos', 'grupo') DEFAULT 'publico',
    descricao TEXT,
    data_publicacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (utilizador_id) REFERENCES utilizador(id),
    FOREIGN KEY (percurso_id) REFERENCES percurso(id)
);


CREATE TABLE comentario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    publicacao_id INT NOT NULL,
    utilizador_id INT NOT NULL,
    texto TEXT NOT NULL,
    data_comentario TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (publicacao_id) REFERENCES publicacao(id),
    FOREIGN KEY (utilizador_id) REFERENCES utilizador(id)
);


CREATE VIEW ranking_utilizadores AS
SELECT 
    u.id,
    u.nome,
    u.username,
    u.pontos,
    u.total_distancia_km,
    u.total_corridas,
    u.total_territorios_conquistados,
    u.nivel,
    COUNT(DISTINCT c.id) AS conquistas
FROM utilizador u
LEFT JOIN conquista c ON u.id = c.utilizador_id
GROUP BY u.id, u.nome, u.username, u.pontos, u.total_distancia_km, u.total_corridas, u.total_territorios_conquistados, u.nivel
ORDER BY u.pontos DESC, u.total_distancia_km DESC;


CREATE VIEW leaderboard_completa AS
SELECT 
    u.id,
    u.nome,
    u.username,
    u.pontos,
    u.total_distancia_km,
    u.total_corridas,
    u.total_territorios_conquistados,
    u.nivel,
    COUNT(DISTINCT t.id) AS territorios_atuais
FROM utilizador u
LEFT JOIN territorio t ON t.conquistador_atual_id = u.id
GROUP BY u.id, u.nome, u.username, u.pontos, u.total_distancia_km, u.total_corridas, u.total_territorios_conquistados, u.nivel
ORDER BY u.pontos DESC;


DELIMITER //
CREATE TRIGGER atualizar_pontos_apos_conquista
AFTER INSERT ON conquista
FOR EACH ROW
BEGIN
    UPDATE utilizador
    SET pontos = pontos + 10,
        total_territorios_conquistados = total_territorios_conquistados + 1
    WHERE id = NEW.utilizador_id;
END //
DELIMITER ;


DELIMITER //
CREATE TRIGGER atualizar_stats_apos_percurso
AFTER UPDATE ON percurso
FOR EACH ROW
BEGIN
    IF NEW.estado = 'CONCLUIDO' AND OLD.estado != 'CONCLUIDO' THEN
        UPDATE utilizador
        SET total_distancia_km = total_distancia_km + NEW.distancia_km,
            total_corridas = total_corridas + 1
        WHERE id = NEW.utilizador_id;
    END IF;
END //
DELIMITER ;



-- Inserir utilizadores (password: 123456 encriptada com BCrypt)
INSERT INTO utilizador (nome, username, email, password, sexo, altura_cm, peso_kg, pontos, total_distancia_km, total_corridas)
VALUES
('Miguel Croca', 'miguelcroca', 'miguel@gmail.com', '$2a$10$YourBCryptHashHere', 'Masculino', 178.00, 75.00, 387, 15.50, 3),
('Bento do Rey', 'bentodorey', 'bento@gmail.com', '$2a$10$YourBCryptHashHere', 'Masculino', 182.00, 80.00, 420, 16.80, 2),
('Martim F', 'martimf', 'martim@gmail.com', '$2a$10$YourBCryptHashHere', 'Masculino', 175.00, 70.00, 300, 12.00, 2),
('Vasco', 'vasco', 'vasco@gmail.com', '$2a$10$YourBCryptHashHere', 'Masculino', 180.00, 77.00, 137, 5.50, 1);

-- Inserir territórios
INSERT INTO territorio (nome, descricao, latitude, longitude)
VALUES
('Lisboa Centro', 'Área central da cidade de Lisboa', 38.7169, -9.1399),
('Parque das Nações', 'Zona moderna junto ao rio Tejo', 38.7686, -9.0945),
('Belém', 'Zona histórica de Lisboa', 38.6979, -9.2061);



SELECT 'Base de dados criada com sucesso!' AS status;
SHOW TABLES;

-- Ver utilizadores criados
SELECT id, nome, username, pontos, total_distancia_km AS 'kms', total_corridas AS 'corridas'
FROM utilizador
ORDER BY pontos DESC;