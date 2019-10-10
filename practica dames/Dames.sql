
DROP TABLE IF EXISTS Usuarios;
DROP TABLE IF EXISTS Puntuaciones;

CREATE TABLE Usuarios(nombre VARCHAR(20) NOT NULL PRIMARY KEY,
                        contraseña VARCHAR(20) not null);

CREATE TABLE Puntuaciones (nombre1 VARCHAR(20) NOT NULL,
                            puntuacion1 VARCHAR(20) not null,
                            nombre2 VARCHAR(20) NOT NULL,
                            puntuacion2 VARCHAR(20) not null,
                            fecha Datetime);
