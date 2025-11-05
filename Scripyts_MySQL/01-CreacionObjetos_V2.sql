-- ======================================================
-- CREACIÓN DE OBJETOS V2 - Complejo y optimizado
-- Sistema de Apuestas - BosqPlayDB
-- ======================================================

DROP DATABASE IF EXISTS BosqPlayDB;
CREATE DATABASE BosqPlayDB;
USE BosqPlayDB;

CREATE TABLE TipoJuego (
    idTipoJuego INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(255)
);

CREATE TABLE Empleado (
    idEmpleado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    cargo VARCHAR(50) NOT NULL,
    activo CHAR(1) DEFAULT 'S' CHECK (activo IN ('S','N')),
    fechaIngreso DATE DEFAULT CURRENT_DATE
);

CREATE INDEX idx_empleado_activo ON Empleado(activo);

CREATE TABLE Rol (
    idRol INT AUTO_INCREMENT PRIMARY KEY,
    nombreRol VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE EmpleadoRol (
    idEmpleado INT,
    idRol INT,
    fechaAsignacion DATE DEFAULT CURRENT_DATE,
    PRIMARY KEY (idEmpleado, idRol),
    FOREIGN KEY (idEmpleado) REFERENCES Empleado(idEmpleado) ON DELETE CASCADE,
    FOREIGN KEY (idRol) REFERENCES Rol(idRol) ON DELETE CASCADE
);

CREATE TABLE Cliente (
    idCliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    docId VARCHAR(20) UNIQUE NOT NULL,
    fechaRegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    telefono VARCHAR(20),
    email VARCHAR(100),
    estado VARCHAR(20) DEFAULT 'Activo'
);

CREATE TABLE MetodoPago (
    idMetodo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Transaccion (
    idTransaccion INT AUTO_INCREMENT PRIMARY KEY,
    idEmpleado INT,
    idMetodo INT,
    idCliente INT,
    fechaHora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    monto DECIMAL(12,2) CHECK(monto > 0),
    tipo VARCHAR(20),
    observacion VARCHAR(255),
    FOREIGN KEY (idEmpleado) REFERENCES Empleado(idEmpleado) ON UPDATE CASCADE,
    FOREIGN KEY (idMetodo) REFERENCES MetodoPago(idMetodo),
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente)
);

CREATE INDEX idx_transaccion_cliente ON Transaccion(idCliente);

CREATE TABLE EventoJuego (
    idEvento INT AUTO_INCREMENT PRIMARY KEY,
    idTipoJuego INT,
    fechaAlta DATE DEFAULT CURRENT_DATE,
    estado VARCHAR(20) DEFAULT 'Disponible',
    FOREIGN KEY (idTipoJuego) REFERENCES TipoJuego(idTipoJuego)
);

CREATE TABLE Torneo (
    idEvento INT PRIMARY KEY,
    nombre VARCHAR(100),
    fechaInicio DATE,
    fechaFin DATE,
    buyIn DECIMAL(10,2),
    premio DECIMAL(10,2),
    FOREIGN KEY (idEvento) REFERENCES EventoJuego(idEvento) ON DELETE CASCADE
);

CREATE TABLE Mesa (
    idEvento INT PRIMARY KEY,
    nroMesa INT,
    limiteJugadores INT CHECK(limiteJugadores > 0),
    FOREIGN KEY (idEvento) REFERENCES EventoJuego(idEvento) ON DELETE CASCADE
);

CREATE TABLE Maquina (
    idEvento INT PRIMARY KEY,
    codigoSerie VARCHAR(50) UNIQUE,
    denominacion DECIMAL(10,2),
    FOREIGN KEY (idEvento) REFERENCES EventoJuego(idEvento) ON DELETE CASCADE
);

CREATE TABLE Apuesta (
    idApuesta INT AUTO_INCREMENT PRIMARY KEY,
    idCliente INT,
    idEvento INT,
    idEmpleado INT,
    fechaHora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    monto DECIMAL(10,2) CHECK(monto > 0),
    resultado VARCHAR(20) DEFAULT 'En juego',
    gananciaPerdida DECIMAL(10,2),
    estado VARCHAR(20),
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente),
    FOREIGN KEY (idEvento) REFERENCES EventoJuego(idEvento),
    FOREIGN KEY (idEmpleado) REFERENCES Empleado(idEmpleado)
);

CREATE TABLE InventarioFicha (
    idInventario INT AUTO_INCREMENT PRIMARY KEY,
    nombreBoveda VARCHAR(50),
    saldo INT DEFAULT 0 CHECK(saldo >= 0)
);

CREATE TABLE MovimientoFicha (
    idMov INT AUTO_INCREMENT PRIMARY KEY,
    idInventario INT,
    idEmpleado INT,
    fechaHora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    cantidad INT CHECK(cantidad <> 0),
    tipoMov VARCHAR(20),
    observacion VARCHAR(255),
    FOREIGN KEY (idInventario) REFERENCES InventarioFicha(idInventario),
    FOREIGN KEY (idEmpleado) REFERENCES Empleado(idEmpleado)
);
