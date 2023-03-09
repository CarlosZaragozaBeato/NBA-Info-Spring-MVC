-- Creación de la tabla Equipos
CREATE TABLE Equipos (
    id_equipo NUMBER(10) PRIMARY KEY,
    imagen VARCHAR2(200) ,
    nombre VARCHAR2(50) ,
    pais VARCHAR2(50),
    conferencia VARCHAR2(20)
);

-- Creación de la tabla Jugadores
CREATE TABLE Jugadores (
    id_jugador NUMBER(10) PRIMARY KEY,
    nombre VARCHAR2(50) NOT NULL,
    apellido VARCHAR2(50) NOT NULL,
    total_puntos NUMBER(6),
    total_asistencias NUMBER(6),
    id_equipo NUMBER(10),
    CONSTRAINT fk_jugadores_equipos FOREIGN KEY (id_equipo)
        REFERENCES Equipos(id_equipo)
        ON DELETE CASCADE
);

-- Creación de la tabla Partidos
CREATE TABLE Partidos (
    id_partido NUMBER(10) PRIMARY KEY,
    temporada VARCHAR2(10) NOT NULL,
    id_equipo_local NUMBER(10),
    id_equipo_visitante NUMBER(10),
    puntos_local NUMBER(3),
    puntos_visitante NUMBER(3),
    CONSTRAINT fk_partidos_equipos_local FOREIGN KEY (id_equipo_local)
        REFERENCES Equipos(id_equipo),
    CONSTRAINT fk_partidos_equipos_visitante FOREIGN KEY (id_equipo_visitante)
        REFERENCES Equipos(id_equipo)
);





-- Inserción de equipos
INSERT INTO Equipos (id_equipo, nombre,imagen,conferencia, pais) VALUES (1, 'Los Angeles Lakers','https://i.postimg.cc/V6SW-BFcD/1200px-Los-Angeles-Lakers-logo-svg.png','OESTE', 'Estados Unidos');
INSERT INTO Equipos (id_equipo, nombre,imagen,conferencia, pais) VALUES (2, 'Golden State Warriors','https://i.postimg.cc/j2P6Bd2f/Golden-State-Warriors-logo-svg.png','ESTE','Estados Unidos');
INSERT INTO Equipos (id_equipo, nombre,imagen,conferencia, pais) VALUES (3, 'Miami Heat', 'https://i.postimg.cc/63DLHGWw/Miami-Heat-logo-svg.png','OESTE','Estados Unidos');
INSERT INTO Equipos (id_equipo, imagen, nombre, pais, conferencia) VALUES (4, 'https://i.postimg.cc/d34ZXcTG/celtics.png',
'Boston Celtics', 'Estados Unidos', 'Este');
INSERT INTO Equipos (id_equipo, imagen, nombre, pais, conferencia) VALUES (5, 'https://i.postimg.cc/1t0qGqmC/spurs.png',
'San Antonio Spurs', 'Estados Unidos', 'Oeste');
INSERT INTO Equipos (id_equipo, imagen, nombre, pais, conferencia) VALUES (6, 'https://i.postimg.cc/zG9b4j32/logo-Denver-Nuggets.png',
'Denver Nuggets', 'Estados Unidos', 'Oeste');
INSERT INTO Equipos (id_equipo, imagen, nombre, pais, conferencia) VALUES (7, 'https://i.postimg.cc/PJt825Nt/Dallas-Mavericks-logo-svg.png',
'Dallas Maverics', 'Estados Unidos', 'Oeste');



-- Inserción de jugadores
INSERT INTO Jugadores (id_jugador, nombre, apellido, total_puntos,total_asistencias, id_equipo) VALUES (1, 'LeBron', 'James', 41000,12000, 2);
INSERT INTO Jugadores (id_jugador, nombre, apellido,  total_puntos,total_asistencias,  id_equipo) VALUES (2, 'Stephen', 'Curry', 35000,10000, 1);
INSERT INTO Jugadores (id_jugador, nombre, apellido,  total_puntos,total_asistencias,  id_equipo) VALUES (3, 'Dwyane', 'Wade', 25000,8000, 4);

-- Inserción de partidos
INSERT INTO Partidos (id_partido, temporada, id_equipo_local, id_equipo_visitante, puntos_local, puntos_visitante) VALUES (1, '2022', 1, 2, 105, 110);
INSERT INTO Partidos (id_partido, temporada, id_equipo_local, id_equipo_visitante, puntos_local, puntos_visitante) VALUES (2, '2020', 2, 3, 98, 95);
INSERT INTO Partidos (id_partido, temporada, id_equipo_local, id_equipo_visitante, puntos_local, puntos_visitante) VALUES (3, '2015', 3, 1, 110, 105);
