-- Eliminar la tabla usuario_rol si existe
DROP TABLE IF EXISTS usuario_rol;

-- Eliminar la tabla usuario si existe
DROP TABLE IF EXISTS usuario;

-- Eliminar la tabla rol si existe
DROP TABLE IF EXISTS rol;

-- Crear la tabla usuario
CREATE TABLE usuario (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    contrasenia TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL
);

-- Crear la tabla rol
CREATE TABLE rol (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT UNIQUE NOT NULL
);

-- Crear la tabla intermedia usuario_rol para la relación muchos a muchos
CREATE TABLE usuario_rol (
    usuario_id INTEGER,
    rol_id INTEGER,
    PRIMARY KEY (usuario_id, rol_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (rol_id) REFERENCES rol(id) ON DELETE CASCADE
);

-- Insertar roles en la tabla rol
INSERT INTO rol (nombre) VALUES
    ('Administrador'),
    ('Editor'),
    ('Usuario');

-- Insertar usuarios de ejemplo
INSERT INTO usuario (nombre, contrasenia, email) VALUES
    ('Juan Pérez', 'pass123', 'juan@example.com'),
    ('Ana López', 'securePass', 'ana@example.com'),
    ('Carlos Gómez', 'claveSegura', 'carlos@example.com');

-- Asignar roles a los usuarios (ejemplo)
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES
    (1, 1), -- Juan Pérez es Administrador
    (2, 2), -- Ana López es Editor
    (3, 3), -- Carlos Gómez es Usuario
    (2, 3); -- Ana López también es Usuario

-- Primero, crear una tabla temporal con la nueva estructura
CREATE TABLE tictactoe_stats_temp (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_email TEXT NOT NULL,
    partidas_totales INTEGER DEFAULT 0,
    partidas_ganadas INTEGER DEFAULT 0,
    partidas_empatadas INTEGER DEFAULT 0,
    partidas_perdidas INTEGER DEFAULT 0,
    puntuacion_total INTEGER DEFAULT 0,
    racha_actual INTEGER DEFAULT 0,
    racha_maxima INTEGER DEFAULT 0,
    UNIQUE(user_email)
);

-- Si hay datos en la tabla original, cópialos a la nueva tabla
-- (esto es opcional ya que probablemente no tengas datos aún)
INSERT INTO tictactoe_stats_temp (user_email, partidas_totales, partidas_ganadas,
partidas_empatadas, partidas_perdidas, puntuacion_total,
racha_actual, racha_maxima)
SELECT
    (SELECT email FROM usuario WHERE usuario.id = tictactoe_stats.user_id),
    partidas_totales,
    partidas_ganadas,
    partidas_empatadas,
    partidas_perdidas,
    puntuacion_total,
    racha_actual,
    racha_maxima
FROM tictactoe_stats;

-- Eliminar la tabla original
DROP TABLE IF EXISTS tictactoe_stats;

-- Renombrar la tabla temporal a la original
ALTER TABLE tictactoe_stats_temp RENAME TO tictactoe_stats;