-- =====================================================
-- SCRIPT COMPLETO - BASE DE DADOS WALK4CONQUEST
-- Criação e configuração completa do sistema
-- =====================================================

-- =====================================================
-- 1. CRIAÇÃO DA BASE DE DADOS
-- =====================================================

DROP DATABASE IF EXISTS conquistas_app;
CREATE DATABASE conquistas_app;
USE conquistas_app;

-- =====================================================
-- 2. CRIAÇÃO DAS TABELAS PRINCIPAIS
-- =====================================================

-- Tabela Utilizador
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

-- Tabela Territorio
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

-- Tabela Percurso
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

-- Tabela CoordenadaPercurso
CREATE TABLE coordenada_percurso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    percurso_id INT NOT NULL,
    latitude DECIMAL(10,8) NOT NULL,
    longitude DECIMAL(11,8) NOT NULL,
    timestamp_ponto TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (percurso_id) REFERENCES percurso(id)
);

-- Tabela Conquista
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

-- Tabela HistoricoConquista
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

-- Tabela Publicacao
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

-- Tabela Comentario
CREATE TABLE comentario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    publicacao_id INT NOT NULL,
    utilizador_id INT NOT NULL,
    texto TEXT NOT NULL,
    data_comentario TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (publicacao_id) REFERENCES publicacao(id),
    FOREIGN KEY (utilizador_id) REFERENCES utilizador(id)
);

-- =====================================================
-- 3. CRIAÇÃO DE VIEWS
-- =====================================================

-- View: RankingUtilizadores
CREATE VIEW ranking_utilizadores AS
SELECT 
    u.id,
    u.nome,
    u.pontos,
    COUNT(c.id) AS conquistas,
    SUM(c.distancia_km) AS total_km
FROM utilizador u
LEFT JOIN conquista c ON u.id = c.utilizador_id
GROUP BY u.id, u.nome, u.pontos
ORDER BY u.pontos DESC;

-- View: LeaderboardCompleta
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

-- =====================================================
-- 4. CRIAÇÃO DE TRIGGERS
-- =====================================================

-- Trigger: Atualizar pontos após conquista
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

-- Trigger: Atualizar estatísticas após concluir percurso
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

-- =====================================================
-- 5. DADOS DE TESTE (OPCIONAL)
-- =====================================================

INSERT INTO utilizador (nome, username, email, password, sexo, altura_cm, peso_kg, data_nascimento, pontos)
VALUES
('João Silva', 'joaosilva', 'joao@gmail.com', '$2a$10$dummyhash1', 'Masculino', 178.00, 72.50, '1998-02-14', 100),
('Maria Costa', 'mariac', 'maria@gmail.com', '$2a$10$dummyhash2', 'Feminino', 165.00, 58.00, '2000-08-09', 150),
('Tiago Ramos', 'tiagor', 'tiago@gmail.com', '$2a$10$dummyhash3', 'Masculino', 182.00, 80.00, '1996-05-20', 200);

INSERT INTO territorio (nome, descricao, latitude, longitude)
VALUES
('Lisboa Centro', 'Área central da cidade de Lisboa', 38.7169, -9.1399),
('Parque das Nações', 'Zona moderna junto ao rio Tejo', 38.7686, -9.0945);

INSERT INTO percurso (utilizador_id, territorio_id, data_inicio, data_fim, distancia_km, duracao_min, estado)
VALUES
(1, 1, '2025-10-30 10:00:00', '2025-10-30 10:45:00', 5.2, 45, 'CONCLUIDO'),
(2, 2, '2025-10-31 09:00:00', '2025-10-31 09:30:00', 3.0, 30, 'CONCLUIDO');

INSERT INTO coordenada_percurso (percurso_id, latitude, longitude)
VALUES
(1, 38.7160, -9.1400),
(1, 38.7170, -9.1380),
(2, 38.7690, -9.0950),
(2, 38.7700, -9.0930);

INSERT INTO conquista (utilizador_id, territorio_id, percurso_id, distancia_km, duracao_min)
VALUES
(1, 1, 1, 5.2, 45),
(2, 2, 2, 3.0, 30);

INSERT INTO publicacao (utilizador_id, percurso_id, visibilidade, descricao)
VALUES
(1, 1, 'publico', 'Primeira conquista em Lisboa Centro!'),
(2, 2, 'amigos', 'Conquista concluída no Parque das Nações!');

INSERT INTO comentario (publicacao_id, utilizador_id, texto)
VALUES
(1, 2, 'Boa, João! Grande percurso!'),
(2, 1, 'Parabéns Maria! Belo trajeto.');

-- =====================================================
-- 6. VERIFICAÇÃO FINAL
-- =====================================================

SELECT 'Base de dados criada com sucesso!' AS status;
SHOW TABLES;