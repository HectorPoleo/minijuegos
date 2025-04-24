

-- Eliminar la tabla palabra si existe
DROP TABLE IF EXISTS palabra;


-- Crear la tabla palabra
CREATE TABLE palabra (
    palabraString TEXT NOT NULL,
    dificultad TEXT  NOT NULL
);

-- Insertar usuarios de ejemplo
INSERT INTO palabra (palabraString, dificultad) VALUES
     ('loro', 'facil'),
    ('dios', 'facil'),
    ('tenedor', 'medio'),
    ('kitsune', 'dificil'),
    ('burro', 'medio'),
    ('joathan', 'dificil'),
    ('grotesco', 'dificil'),
    ('quimera', 'dificil'),
    ('yokai', 'dificil'),
    ('gato', 'facil');

