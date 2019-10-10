
DROP TABLE IF EXISTS Usuarios;
DROP TABLE IF EXISTS Puntuaciones;

CREATE TABLE Usuarios(nombre VARCHAR(20) NOT NULL PRIMARY KEY,
                        contraseña VARCHAR(20) not null);

CREATE TABLE Puntuaciones (nombre VARCHAR(20) NOT NULL PRIMARY KEY,
                            puntuacion VARCHAR(20) not null);
